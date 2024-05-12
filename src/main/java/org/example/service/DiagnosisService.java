package org.example.service;

import org.example.model.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> getAllDiagnoses();
    Diagnosis getDiagnosisById(long id);
    Diagnosis addDiagnosis(Diagnosis diagnosis);
    Diagnosis updateDiagnosis(long id, Diagnosis diagnosis);
    void deleteDiagnosis(long id);
}
