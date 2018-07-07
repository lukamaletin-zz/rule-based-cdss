package com.lukamaletin.cdss.service;

import com.lukamaletin.cdss.controller.exception.NotFoundException;
import com.lukamaletin.cdss.model.*;
import com.lukamaletin.cdss.model.dto.*;
import com.lukamaletin.cdss.repository.DiseaseRepository;
import com.lukamaletin.cdss.repository.PatientRepository;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReasoningService {

    private final DiseaseRepository diseaseRepository;


    private final PatientRepository patientRepository;

    @Autowired
    public ReasoningService(DiseaseRepository diseaseRepository, PatientRepository patientRepository) {
        this.diseaseRepository = diseaseRepository;
        this.patientRepository = patientRepository;
    }

    public DiagnosisResponse getDiagnosis(DiagnosisRequest diagnosisRequest) {
        final KieSession kieSession = getKieSession();

        // Insert facts:
        kieSession.insert(diagnosisRequest);
        final Patient patient = patientRepository.findByPersonalId(diagnosisRequest.getPatient().getPersonalId())
                .orElseThrow(NotFoundException::new);
        for (Therapy therapy : patient.getTherapies()) {
            kieSession.insert(therapy);
        }

        kieSession.fireAllRules();

        // Get diagnosis suggestion:
        final Collection<?> objects = kieSession.getObjects(o -> o.getClass() == DiagnosisResponse.class);
        if (objects == null || objects.size() < 1) {
            throw new NotFoundException("No disease found!");
        }
        final DiagnosisResponse diagnosisResponse = objects.stream().map(o -> (DiagnosisResponse) o).collect(Collectors.toList()).get(0);

        retractFacts(kieSession);

        return diagnosisResponse;
    }

    public List<DiagnosisResponse> getRelatedDiseases(List<Symptom> symptoms) {
        final KieSession kieSession = getKieSession();

        for (Symptom symptom : symptoms) {
            kieSession.insert(symptom);
        }

        final List<MatchedDisease> matchedDiseases = new ArrayList<>();
        final QueryResults results = kieSession.getQueryResults("Get diseases related to symptoms");
        if (results == null) {
            throw new NotFoundException("No disease found!");
        }

        for (QueryResultsRow row : results) {
            matchedDiseases.add(new MatchedDisease(((Long) row.get("$matched")).floatValue(), (Disease) row.get("$disease")));
        }

        matchedDiseases.sort((o1, o2) -> (int) (o2.getPercentageMatched() - o1.getPercentageMatched()));
        final List<DiagnosisResponse> diagnosisResponses = matchedDiseases.stream()
                .map(d -> new DiagnosisResponse(d.getPercentageMatched(), new DiseaseResponse(d.getDisease())))
                .collect(Collectors.toList());

        retractFacts(kieSession);

        return diagnosisResponses;
    }

    public List<SymptomResponse> getDiseaseSymptoms(String diseaseName) {
        final KieSession kieSession = getKieSession();

        final QueryResults results = kieSession.getQueryResults("Get disease symptoms", diseaseName);
        if (!results.iterator().hasNext()) {
            throw new NotFoundException("Disease not found!");
        }

        final QueryResultsRow row = results.iterator().next();
        final Collection<?> objects = (Collection<?>) row.get("$symptoms");
        final List<SymptomResponse> symptomResponses = objects.stream()
                .map(o -> (SymptomDisease) o)
                .sorted((o1, o2) -> (o2.isSpecific() ? 1 : 0) - (o1.isSpecific() ? 1 : 0))
                .map(s -> new SymptomResponse(s.getSymptom()))
                .collect(Collectors.toList());

        retractFacts(kieSession);

        return symptomResponses;
    }

    public List<Medication> validateTherapy(Therapy therapy) {
        final KieSession kieSession = getKieSession();

        final Patient patient = patientRepository.findByPersonalId(therapy.getPatient().getPersonalId())
                .orElseThrow(() -> new NotFoundException("Patient not found!"));
        for (Ingredient ingredient : patient.getAllergies()) {
            kieSession.insert(ingredient);
        }

        for (Medication medication : therapy.getMedications()) {
            kieSession.insert(medication);
        }

        final List<Medication> medications = new ArrayList<>();
        final QueryResults results = kieSession.getQueryResults("Get prescribed medications with allergies");

        for (QueryResultsRow row : results) {
            medications.add((Medication) row.get("$medication"));
        }

        retractFacts(kieSession);

        return medications;
    }

    public ReportResponse getReports() {
        final KieSession kieSession = getKieSession();

        for (Patient patient : patientRepository.findAll()) {
            kieSession.insert(patient);
        }

        final ReportResponse reportResponse = new ReportResponse();

        reportResponse.setChronicallyIllPatients(getPatientsForReport(kieSession, "Get potentially chronically ill patients"));
        reportResponse.setAddictedPatients(getPatientsForReport(kieSession, "Get potentially addicted patients"));
        reportResponse.setWeakenedImmunityPatients(getPatientsForReport(kieSession, "Get weakened immunity patients"));

        retractFacts(kieSession);

        return reportResponse;
    }

    private List<Patient> getPatientsForReport(KieSession kieSession, String queryName) {
        final List<Patient> patients = new ArrayList<>();
        final QueryResults results = kieSession.getQueryResults(queryName);

        for (QueryResultsRow row : results) {
            patients.add((Patient) row.get("$patient"));
        }

        return patients;
    }

    public void fillSession() {
        final KieSession kieSession = getKieSession();

        final List<Disease> diseases = diseaseRepository.findAll();
        for (Disease disease : diseases) {
            // Insert deep cloned object so Hibernate doesn't set nested objects to null.
            kieSession.insert(new Disease(disease));
        }
    }

    private void retractFacts(KieSession kieSession) {
        final List<FactHandle> handles = new ArrayList<>(kieSession.getFactHandles(f -> f.getClass() != Disease.class));
        for (FactHandle handle : handles) {
            kieSession.delete(handle);
        }
    }

    private KieSession getKieSession() {
        return (KieSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
