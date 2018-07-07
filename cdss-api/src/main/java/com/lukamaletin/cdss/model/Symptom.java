package com.lukamaletin.cdss.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String name;

    private boolean complex;

    @OneToMany(mappedBy = "symptom", fetch = FetchType.EAGER)
    private Set<SymptomDisease> diseases;

    public Symptom() {
    }

    public Symptom(@NotEmpty String name) {
        this.name = name;
    }

    @SuppressWarnings("unused") // Used from .drl file.
    public Symptom(@NotEmpty String name, boolean complex) {
        this.name = name;
        this.complex = complex;
    }

    public Symptom(@NotEmpty String name, Set<SymptomDisease> diseases) {
        this.name = name;
        this.diseases = diseases;
    }

    public Symptom(Symptom symptom) {
        this.id = symptom.getId();
        this.name = symptom.getName();
        this.complex = symptom.isComplex();
        this.diseases = null;
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

    public boolean isComplex() {
        return complex;
    }

    public void setComplex(boolean complex) {
        this.complex = complex;
    }

    public Set<SymptomDisease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<SymptomDisease> diseases) {
        this.diseases = diseases;
    }
}
