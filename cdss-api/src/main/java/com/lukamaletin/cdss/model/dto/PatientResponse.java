package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Ingredient;
import com.lukamaletin.cdss.model.Patient;
import com.lukamaletin.cdss.model.Therapy;

import java.util.Date;
import java.util.Set;

public class PatientResponse {

    private long id;

    private String personalId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Date dateOfBirth;

    private Set<Therapy> therapies;

    private Set<Ingredient> allergies;

    public PatientResponse() {
    }

    public PatientResponse(Patient patient) {
        this.id = patient.getId();
        this.personalId = patient.getPersonalId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.phoneNumber = patient.getPhoneNumber();
        this.dateOfBirth = patient.getDateOfBirth();
        this.therapies = patient.getTherapies();
        this.allergies = patient.getAllergies();

        for (Therapy therapy : this.therapies) {
            therapy.setPatient(null);
            if (therapy.getDisease() != null) {
                therapy.getDisease().setSymptoms(null);
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Therapy> getTherapies() {
        return therapies;
    }

    public void setTherapies(Set<Therapy> therapies) {
        this.therapies = therapies;
    }

    public Set<Ingredient> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Ingredient> allergies) {
        this.allergies = allergies;
    }
}
