package com.lukamaletin.cdss.model.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1m")
public class HeartbeatEvent {

    private MonitoredPatient patient;

    public HeartbeatEvent() {
    }

    public HeartbeatEvent(MonitoredPatient patient) {
        this.patient = patient;
    }

    public MonitoredPatient getPatient() {
        return patient;
    }

    public void setPatient(MonitoredPatient patient) {
        this.patient = patient;
    }
}
