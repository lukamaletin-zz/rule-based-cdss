package com.lukamaletin.cdss.service;

import com.lukamaletin.cdss.model.monitoring.HeartbeatEvent;
import com.lukamaletin.cdss.model.monitoring.MonitoredPatient;
import com.lukamaletin.cdss.model.monitoring.OxygenChangeEvent;
import com.lukamaletin.cdss.model.monitoring.UrinationEvent;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitoringServiceTests {

    @Value("${rules.groupId}")
    private String groupId;

    @Value("${rules.artifactId}")
    private String artifactId;

    @Value("${rules.version}")
    private String version;

    @Autowired
    private MonitoringService monitoringService;

    private KieSession kieSession() {
        final KieServices kieServices = KieServices.Factory.get();

        final KieBaseConfiguration kieBaseConfig = kieServices.newKieBaseConfiguration();
        kieBaseConfig.setOption(EventProcessingOption.STREAM);

        final KieSessionConfiguration kieSessionConfig = kieServices.newKieSessionConfiguration();
        kieSessionConfig.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));

        final KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId(groupId, artifactId, version));

        final KieSession kieSession = kieContainer.newKieBase(kieBaseConfig).newKieSession(kieSessionConfig, null);
        kieSession.setGlobal("service", monitoringService);
        return kieSession;
    }

    private int fireAllRules(KieSession kieSession) {
        kieSession.getAgenda().getAgendaGroup("monitoring").setFocus();
        return kieSession.fireAllRules();
    }

    @Test
    public void testProblemWithOxygenRule() {
        final KieSession kieSession = kieSession();

        final MonitoredPatient patient = new MonitoredPatient("p1", false);
        kieSession.insert(patient);

        kieSession.insert(new OxygenChangeEvent(patient, 50));
        assertEquals(1, fireAllRules(kieSession));

        kieSession.insert(new OxygenChangeEvent(patient, 100));
        assertEquals(0, fireAllRules(kieSession));

        final SessionPseudoClock pseudoClock = kieSession.getSessionClock();
        pseudoClock.advanceTime(15, TimeUnit.MINUTES);

        kieSession.insert(new OxygenChangeEvent(patient, 50));
        assertEquals(1, fireAllRules(kieSession));
    }

    @Test
    public void testFastHeartRateRule() {
        final KieSession kieSession = kieSession();

        final MonitoredPatient patient = new MonitoredPatient("p1", false);
        kieSession.insert(patient);

        for (int i = 0; i < 25; i++) {
            kieSession.insert(new HeartbeatEvent(patient));
        }
        assertEquals(0, fireAllRules(kieSession));

        kieSession.insert(new HeartbeatEvent(patient));
        assertEquals(1, fireAllRules(kieSession));

        final SessionPseudoClock pseudoClock = kieSession.getSessionClock();
        pseudoClock.advanceTime(10, TimeUnit.SECONDS);

        assertEquals(0, fireAllRules(kieSession));
    }

    @Test
    public void testDialysisRequiredRule() {
        final KieSession kieSession = kieSession();

        final MonitoredPatient patient = new MonitoredPatient("p1", true);
        kieSession.insert(patient);

        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartbeatEvent(patient));
        }
        assertEquals(0, fireAllRules(kieSession));

        kieSession.insert(new HeartbeatEvent(patient));
        kieSession.insert(new UrinationEvent(patient, 25));
        assertEquals(1, fireAllRules(kieSession));

        kieSession.insert(new UrinationEvent(patient, 25));
        assertEquals(1, fireAllRules(kieSession));

        final SessionPseudoClock pseudoClock = kieSession.getSessionClock();
        pseudoClock.advanceTime(12, TimeUnit.HOURS);

        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartbeatEvent(patient));
        }
        assertEquals(0, fireAllRules(kieSession));

        kieSession.insert(new HeartbeatEvent(patient));
        kieSession.insert(new UrinationEvent(patient, 25));
        assertEquals(1, fireAllRules(kieSession));
    }
}
