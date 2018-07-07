package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
