package com.lukamaletin.cdss.model.dto;

public class DiagnosisResponse {

    private float percentageMatched;

    private DiseaseResponse disease;

    public DiagnosisResponse() {
    }

    public DiagnosisResponse(float percentageMatched, DiseaseResponse disease) {
        this.percentageMatched = percentageMatched;
        this.disease = disease;
    }

    public float getPercentageMatched() {
        return percentageMatched;
    }

    public void setPercentageMatched(float percentageMatched) {
        this.percentageMatched = percentageMatched;
    }

    public DiseaseResponse getDisease() {
        return disease;
    }

    public void setDisease(DiseaseResponse disease) {
        this.disease = disease;
    }
}
