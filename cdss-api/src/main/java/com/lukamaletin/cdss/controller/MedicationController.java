package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.controller.base.BaseController;
import com.lukamaletin.cdss.model.Medication;
import com.lukamaletin.cdss.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lukamaletin.cdss.util.Constants.API_MEDICATIONS;

@RestController
@RequestMapping(API_MEDICATIONS)
public class MedicationController extends BaseController<Medication, Long> {

    @Autowired
    public MedicationController(MedicationRepository repository) {
        super(repository);
    }
}
