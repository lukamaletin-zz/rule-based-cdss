package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.model.dto.DiseaseRequest;
import com.lukamaletin.cdss.model.dto.DiseaseResponse;
import com.lukamaletin.cdss.repository.DiseaseRepository;
import com.lukamaletin.cdss.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.lukamaletin.cdss.util.Constants.API_DISEASES;

@RestController
@RequestMapping(API_DISEASES)
public class DiseaseController {

    private final DiseaseRepository diseaseRepository;

    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseRepository diseaseRepository, DiseaseService diseaseService) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseService = diseaseService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DiseaseRequest data) {
        return new ResponseEntity<>(diseaseService.create(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(diseaseRepository.findAll().stream().map(DiseaseResponse::new), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity<>(diseaseRepository.findById(id).map(DiseaseResponse::new), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid DiseaseRequest data, @PathVariable Long id) {
        return new ResponseEntity<>(diseaseService.update(data, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        diseaseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
