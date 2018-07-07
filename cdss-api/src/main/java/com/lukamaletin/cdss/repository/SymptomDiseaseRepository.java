package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.SymptomDisease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomDiseaseRepository extends JpaRepository<SymptomDisease, Long> {
}
