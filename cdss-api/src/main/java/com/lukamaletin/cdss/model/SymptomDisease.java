package com.lukamaletin.cdss.model;

import javax.persistence.*;

@Entity
public class SymptomDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn
    private Symptom symptom;

    @ManyToOne
    @JoinColumn
    private Disease disease;

    private boolean specific;

    public SymptomDisease() {
    }

    public SymptomDisease(Symptom symptom, Disease disease, boolean specific) {
        this.symptom = symptom;
        this.disease = disease;
        this.specific = specific;
    }

    public SymptomDisease(SymptomDisease symptomDisease) {
        this.id = symptomDisease.getId();
        this.symptom = new Symptom(symptomDisease.getSymptom());
        this.disease = null;
        this.specific = symptomDisease.isSpecific();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public boolean isSpecific() {
        return specific;
    }

    public void setSpecific(boolean specific) {
        this.specific = specific;
    }
}
