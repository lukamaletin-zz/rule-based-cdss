package com.lukamaletin.cdss.model.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15m")
public class OxygenChangeEvent {

    private MonitoredPatient patient;

    private int oxygenLevel;

    public OxygenChangeEvent() {
    }

    public OxygenChangeEvent(MonitoredPatient patient, int oxygenLevel) {
        this.patient = patient;
        this.oxygenLevel = oxygenLevel;
    }

    public MonitoredPatient getPatient() {
        return patient;
    }

    public void setPatient(MonitoredPatient patient) {
        this.patient = patient;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(int oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }
}
