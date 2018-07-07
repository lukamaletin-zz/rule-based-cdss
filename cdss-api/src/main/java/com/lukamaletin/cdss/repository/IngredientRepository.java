package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
