package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Disease;

import java.util.Set;
import java.util.stream.Collectors;

public class DiseaseResponse {

    private long id;

    private String name;

    private int groupNumber;

    private Set<DiseaseSymptomResponse> symptoms;

    public DiseaseResponse() {
    }

    public DiseaseResponse(Disease disease) {
        this.id = disease.getId();
        this.name = disease.getName();
        this.groupNumber = disease.getGroupNumber();
        this.symptoms = disease.getSymptoms().stream().map(s -> new DiseaseSymptomResponse(s.getSymptom(), s.isSpecific())).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<DiseaseSymptomResponse> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<DiseaseSymptomResponse> symptoms) {
        this.symptoms = symptoms;
    }
}
