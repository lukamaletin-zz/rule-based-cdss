package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.controller.base.BaseController;
import com.lukamaletin.cdss.model.Ingredient;
import com.lukamaletin.cdss.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lukamaletin.cdss.util.Constants.API_INGREDIENTS;

@RestController
@RequestMapping(API_INGREDIENTS)
public class IngredientController extends BaseController<Ingredient, Long> {

    @Autowired
    public IngredientController(IngredientRepository repository) {
        super(repository);
    }
}
