package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
