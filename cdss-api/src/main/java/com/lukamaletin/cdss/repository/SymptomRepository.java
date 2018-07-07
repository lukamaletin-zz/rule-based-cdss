package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    List<Symptom> findAllyByComplex(boolean complex);
}
