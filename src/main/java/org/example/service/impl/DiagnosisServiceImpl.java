package org.example.service.impl;

import org.example.dao.DiagnosisDAO;
import org.example.model.Diagnosis;
import org.example.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisDAO diagnosisDAO;

    @Override
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisDAO.getAllDiagnoses();
    }

    @Override
    public Diagnosis getDiagnosisById(long id) {
        return diagnosisDAO.getDiagnosisById(id);
    }

    @Override
    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        return diagnosisDAO.addDiagnosis(diagnosis);
    }

    @Override
    public Diagnosis updateDiagnosis(long id, Diagnosis diagnosis) {
        return diagnosisDAO.updateDiagnosis(id, diagnosis);
    }

    @Override
    public void deleteDiagnosis(long id) {
        diagnosisDAO.deleteDiagnosis(id);
    }
}
