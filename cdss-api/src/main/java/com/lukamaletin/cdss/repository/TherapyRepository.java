package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {
}
