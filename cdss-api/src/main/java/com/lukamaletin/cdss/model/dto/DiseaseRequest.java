package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Disease;
import com.lukamaletin.cdss.model.Symptom;
import com.lukamaletin.cdss.model.SymptomDisease;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiseaseRequest {

    @NotEmpty
    private String name;

    private int groupNumber;

    private List<Symptom> specificSymptoms;

    private List<Symptom> generalSymptoms;

    public DiseaseRequest() {
    }

    public Disease newDisease() {
        final Disease disease = new Disease(name, groupNumber);
        final Set<SymptomDisease> symptoms = new HashSet<>();
        symptoms.addAll(specificSymptoms.stream().map(s -> new SymptomDisease(s, disease, true)).collect(Collectors.toSet()));
        symptoms.addAll(generalSymptoms.stream().map(s -> new SymptomDisease(s, disease, false)).collect(Collectors.toSet()));
        disease.setSymptoms(symptoms);

        return disease;
    }

    public Disease updatedDisease(Disease disease) {
        disease.setName(name);
        disease.setGroupNumber(groupNumber);
        final Set<SymptomDisease> symptoms = new HashSet<>();
        symptoms.addAll(specificSymptoms.stream().map(s -> new SymptomDisease(s, disease, true)).collect(Collectors.toSet()));
        symptoms.addAll(generalSymptoms.stream().map(s -> new SymptomDisease(s, disease, false)).collect(Collectors.toSet()));
        disease.setSymptoms(symptoms);

        return disease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Symptom> getSpecificSymptoms() {
        return specificSymptoms;
    }

    public void setSpecificSymptoms(List<Symptom> specificSymptoms) {
        this.specificSymptoms = specificSymptoms;
    }

    public List<Symptom> getGeneralSymptoms() {
        return generalSymptoms;
    }

    public void setGeneralSymptoms(List<Symptom> generalSymptoms) {
        this.generalSymptoms = generalSymptoms;
    }
}
