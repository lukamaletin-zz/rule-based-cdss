package com.lukamaletin.cdss.model;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Entity
@Role(Role.Type.EVENT)
@Timestamp("prescriptionDate")
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date prescriptionDate;

    private boolean chronic;

    private boolean surgeryPerformed;

    private boolean highBloodPressure;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    @ManyToOne
    @JoinColumn
    private User prescribedBy;

    @ManyToOne
    @JoinColumn
    private Disease disease;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private Set<Medication> medications;

    public Therapy() {
    }

    public Therapy(@NotNull Date prescriptionDate, boolean chronic, boolean surgeryPerformed, boolean highBloodPressure, Patient patient, User prescribedBy, Disease disease, Set<Medication> medications) {
        this.prescriptionDate = prescriptionDate;
        this.chronic = chronic;
        this.surgeryPerformed = surgeryPerformed;
        this.highBloodPressure = highBloodPressure;
        this.patient = patient;
        this.prescribedBy = prescribedBy;
        this.disease = disease;
        this.medications = medications;
    }

    @SuppressWarnings("unused") // Used from .drl file.
    public boolean sufferingDaysMoreThan(int days) {
        return new Date().getTime() - prescriptionDate.getTime() > MILLISECONDS.convert(days, DAYS);
    }

    @SuppressWarnings("unused") // Used from .drl file.
    public boolean sufferingDaysLessThan(int days) {
        return new Date().getTime() - prescriptionDate.getTime() < MILLISECONDS.convert(days, DAYS);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public boolean isChronic() {
        return chronic;
    }

    public void setChronic(boolean chronic) {
        this.chronic = chronic;
    }

    public boolean isSurgeryPerformed() {
        return surgeryPerformed;
    }

    public void setSurgeryPerformed(boolean surgeryPerformed) {
        this.surgeryPerformed = surgeryPerformed;
    }

    public boolean isHighBloodPressure() {
        return highBloodPressure;
    }

    public void setHighBloodPressure(boolean highBloodPressure) {
        this.highBloodPressure = highBloodPressure;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getPrescribedBy() {
        return prescribedBy;
    }

    public void setPrescribedBy(User prescribedBy) {
        this.prescribedBy = prescribedBy;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }
}
