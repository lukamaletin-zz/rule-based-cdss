package com.lukamaletin.cdss.model;

public class MatchedDisease {

    private float percentageMatched;

    private Disease disease;

    public MatchedDisease() {
    }

    public MatchedDisease(float percentageMatched, Disease disease) {
        this.percentageMatched = percentageMatched;
        this.disease = disease;
    }

    public float getPercentageMatched() {
        return percentageMatched;
    }

    public void setPercentageMatched(float percentageMatched) {
        this.percentageMatched = percentageMatched;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
