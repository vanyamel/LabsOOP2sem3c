package org.example.controller;

import org.example.model.Diagnosis;
import org.example.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnoses")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisService.getAllDiagnoses();
    }

    @GetMapping("/{id}")
    public Diagnosis getDiagnosisById(@PathVariable("id") long id) {
        return diagnosisService.getDiagnosisById(id);
    }

    @PostMapping
    public Diagnosis addDiagnosis(@RequestBody Diagnosis diagnosis) {
        return diagnosisService.addDiagnosis(diagnosis);
    }

    @PutMapping("/{id}")
    public Diagnosis updateDiagnosis(@PathVariable("id") long id, @RequestBody Diagnosis diagnosis) {
        return diagnosisService.updateDiagnosis(id, diagnosis);
    }

    @DeleteMapping("/{id}")
    public void deleteDiagnosis(@PathVariable("id") long id) {
        diagnosisService.deleteDiagnosis(id);
    }
}
