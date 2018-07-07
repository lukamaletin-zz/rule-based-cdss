package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.model.Symptom;
import com.lukamaletin.cdss.model.Therapy;
import com.lukamaletin.cdss.model.dto.DiagnosisRequest;
import com.lukamaletin.cdss.service.ReasoningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.lukamaletin.cdss.util.Constants.API_REASONING;

@RestController
@RequestMapping(API_REASONING)
@PreAuthorize("hasAuthority('MEDIC')")
public class ReasoningController {

    private final ReasoningService reasoningService;

    public ReasoningController(ReasoningService reasoningService) {
        this.reasoningService = reasoningService;
    }

    @PostMapping("/diagnose")
    public ResponseEntity getDiagnosis(@RequestBody @Valid DiagnosisRequest diagnosisRequest) {
        return new ResponseEntity<>(reasoningService.getDiagnosis(diagnosisRequest), HttpStatus.OK);
    }

    @PostMapping("/related")
    public ResponseEntity getRelatedDiseases(@RequestBody List<Symptom> symptoms) {
        return new ResponseEntity<>(reasoningService.getRelatedDiseases(symptoms), HttpStatus.OK);
    }

    @PostMapping("/symptoms")
    public ResponseEntity getDiseaseSymptoms(@RequestBody String diseaseName) {
        return new ResponseEntity<>(reasoningService.getDiseaseSymptoms(diseaseName), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity validateTherapy(@RequestBody Therapy therapy) {
        return new ResponseEntity<>(reasoningService.validateTherapy(therapy), HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity getReports() {
        return new ResponseEntity<>(reasoningService.getReports(), HttpStatus.OK);
    }
}
