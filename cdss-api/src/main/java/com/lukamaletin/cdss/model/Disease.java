package com.lukamaletin.cdss.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String name;

    private int groupNumber;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SymptomDisease> symptoms;

    public Disease() {
    }

    public Disease(@NotEmpty String name, int groupNumber) {
        this.name = name;
        this.groupNumber = groupNumber;
    }

    public Disease(@NotEmpty String name, Set<SymptomDisease> symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }

    public Disease(Disease disease) {
        this.id = disease.getId();
        this.name = disease.getName();
        this.groupNumber = disease.getGroupNumber();
        this.symptoms = new HashSet<>();
        for (SymptomDisease symptomDisease : disease.getSymptoms()) {
            symptoms.add(new SymptomDisease(symptomDisease));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Set<SymptomDisease> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<SymptomDisease> symptoms) {
        this.symptoms = symptoms;
    }
}
