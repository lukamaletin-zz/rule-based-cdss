package com.lukamaletin.cdss.service;

import com.lukamaletin.cdss.controller.exception.NotFoundException;
import com.lukamaletin.cdss.model.Disease;
import com.lukamaletin.cdss.model.SymptomDisease;
import com.lukamaletin.cdss.model.dto.DiseaseRequest;
import com.lukamaletin.cdss.repository.DiseaseRepository;
import com.lukamaletin.cdss.repository.SymptomDiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    private final SymptomDiseaseRepository symptomDiseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository, SymptomDiseaseRepository symptomDiseaseRepository) {
        this.diseaseRepository = diseaseRepository;
        this.symptomDiseaseRepository = symptomDiseaseRepository;
    }

    public Disease create(DiseaseRequest data) {
        final Disease disease = data.newDisease();
        return diseaseRepository.save(disease);
    }

    public Disease update(DiseaseRequest data, Long id) {
        Disease persisted = diseaseRepository.findById(id).orElseThrow(NotFoundException::new);
        final Set<SymptomDisease> symptomDiseases = persisted.getSymptoms();
        persisted.setSymptoms(null);
        persisted = diseaseRepository.save(persisted);

        for (SymptomDisease symptomDisease : symptomDiseases) {
            symptomDiseaseRepository.delete(symptomDisease);
        }

        final Disease disease = data.updatedDisease(persisted);
        return diseaseRepository.save(disease);
    }
}
