package com.lukamaletin.cdss.service;

import com.lukamaletin.cdss.model.monitoring.MonitoredPatient;
import com.lukamaletin.cdss.model.monitoring.OxygenChangeEvent;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    private final KieSession kieSession;

    private final MonitoredPatient patient = new MonitoredPatient("p1", true);

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MonitoringService(KieBase kieBase, SimpMessagingTemplate messagingTemplate) {
        this.kieSession = kieBase.newKieSession();
        this.messagingTemplate = messagingTemplate;

        kieSession.insert(patient);
        kieSession.setGlobal("service", this);
    }

    @Scheduled(cron = "1 */1 * * * ?") // Run once every minute.
    private void simulateOxygenChangeEvent() {
        kieSession.insert(new OxygenChangeEvent(patient, 60));
        kieSession.getAgenda().getAgendaGroup("monitoring").setFocus();
        kieSession.fireAllRules();
    }


    @SuppressWarnings("unused") // Used from .drl file.
    public void sendNotification(String patientPersonalId, String ruleName) {
        final String message = String.format("Patient %s - %s", patientPersonalId, ruleName);
        messagingTemplate.convertAndSend("/monitoring", message);
    }
}
