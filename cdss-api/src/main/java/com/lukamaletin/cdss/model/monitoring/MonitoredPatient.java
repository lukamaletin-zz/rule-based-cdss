package com.lukamaletin.cdss.model.monitoring;

public class MonitoredPatient {

    private String personalId;

    private boolean chronicKidneyDisease;

    public MonitoredPatient() {
    }

    public MonitoredPatient(String personalId, boolean chronicKidneyDisease) {
        this.personalId = personalId;
        this.chronicKidneyDisease = chronicKidneyDisease;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public boolean isChronicKidneyDisease() {
        return chronicKidneyDisease;
    }

    public void setChronicKidneyDisease(boolean chronicKidneyDisease) {
        this.chronicKidneyDisease = chronicKidneyDisease;
    }
}
