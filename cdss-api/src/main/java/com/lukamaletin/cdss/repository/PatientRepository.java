package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPersonalId(String personalId);
}
