package com.lukamaletin.cdss.model.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("12h")
public class UrinationEvent {

    private MonitoredPatient patient;

    private int amount;

    public UrinationEvent() {
    }

    public UrinationEvent(MonitoredPatient patient, int amount) {
        this.patient = patient;
        this.amount = amount;
    }

    public MonitoredPatient getPatient() {
        return patient;
    }

    public void setPatient(MonitoredPatient patient) {
        this.patient = patient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
