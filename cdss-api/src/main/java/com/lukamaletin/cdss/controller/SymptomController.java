package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.controller.base.BaseController;
import com.lukamaletin.cdss.model.Symptom;
import com.lukamaletin.cdss.model.dto.SymptomResponse;
import com.lukamaletin.cdss.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lukamaletin.cdss.util.Constants.API_SYMPTOMS;

@RestController
@RequestMapping(API_SYMPTOMS)
public class SymptomController extends BaseController<Symptom, Long> {

    @Autowired
    public SymptomController(SymptomRepository repository) {
        super(repository);
    }

    @Override
    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(repository.findAll().stream().map(SymptomResponse::new), HttpStatus.OK);
    }

    @GetMapping("/simple")
    public ResponseEntity findAllSimple() {
        return new ResponseEntity<>(((SymptomRepository) repository).findAllyByComplex(false).stream().map(SymptomResponse::new), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).map(SymptomResponse::new), HttpStatus.OK);
    }
}
