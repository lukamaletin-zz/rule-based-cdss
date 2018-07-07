package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Patient;

import java.util.List;

public class ReportResponse {

    private List<Patient> chronicallyIllPatients;

    private List<Patient> addictedPatients;

    private List<Patient> weakenedImmunityPatients;

    public ReportResponse() {
    }

    public List<Patient> getChronicallyIllPatients() {
        return chronicallyIllPatients;
    }

    public void setChronicallyIllPatients(List<Patient> chronicallyIllPatients) {
        this.chronicallyIllPatients = chronicallyIllPatients;
    }

    public List<Patient> getAddictedPatients() {
        return addictedPatients;
    }

    public void setAddictedPatients(List<Patient> addictedPatients) {
        this.addictedPatients = addictedPatients;
    }

    public List<Patient> getWeakenedImmunityPatients() {
        return weakenedImmunityPatients;
    }

    public void setWeakenedImmunityPatients(List<Patient> weakenedImmunityPatients) {
        this.weakenedImmunityPatients = weakenedImmunityPatients;
    }
}
