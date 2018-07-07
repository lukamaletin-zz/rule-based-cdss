package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Symptom;

public class DiseaseSymptomResponse {

    private long id;

    private String name;

    private boolean specific;

    public DiseaseSymptomResponse() {
    }

    public DiseaseSymptomResponse(Symptom symptom, boolean specific) {
        this.id = symptom.getId();
        this.name = symptom.getName();
        this.specific = specific;
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

    public boolean isSpecific() {
        return specific;
    }

    public void setSpecific(boolean specific) {
        this.specific = specific;
    }
}
