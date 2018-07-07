package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.controller.base.BaseController;
import com.lukamaletin.cdss.model.Patient;
import com.lukamaletin.cdss.model.dto.PatientResponse;
import com.lukamaletin.cdss.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lukamaletin.cdss.util.Constants.API_PATIENTS;

@RestController
@RequestMapping(API_PATIENTS)
public class PatientController extends BaseController<Patient, Long> {

    @Autowired
    public PatientController(PatientRepository repository) {
        super(repository);
    }

    @Override
    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(repository.findAll().stream().map(PatientResponse::new), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).map(PatientResponse::new), HttpStatus.OK);
    }
}
