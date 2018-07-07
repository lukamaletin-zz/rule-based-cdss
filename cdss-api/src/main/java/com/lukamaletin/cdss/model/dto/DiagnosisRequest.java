package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Patient;
import com.lukamaletin.cdss.model.Symptom;

import javax.validation.constraints.NotNull;
import java.util.List;

public class DiagnosisRequest {

    @NotNull
    private Patient patient;

    private float bodyTemperature;

    private List<Symptom> symptoms;

    public DiagnosisRequest() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public float getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(float bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
