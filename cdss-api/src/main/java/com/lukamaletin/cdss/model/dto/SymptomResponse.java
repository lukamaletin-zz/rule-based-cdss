package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Disease;
import com.lukamaletin.cdss.model.Symptom;

import java.util.Set;

public class SymptomResponse {

    private long id;

    private String name;

    private boolean complex;

    private Set<Disease> diseases;

    public SymptomResponse() {
    }

    public SymptomResponse(Symptom symptom) {
        this.id = symptom.getId();
        this.name = symptom.getName();
        this.complex = symptom.isComplex();
        this.diseases = null;
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

    public boolean isComplex() {
        return complex;
    }

    public void setComplex(boolean complex) {
        this.complex = complex;
    }

    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }
}
