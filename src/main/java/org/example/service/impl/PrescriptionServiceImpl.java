package org.example.service.impl;

import org.example.dao.PrescriptionDAO;
import org.example.model.Prescription;
import org.example.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionDAO prescriptionDAO;

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }

    @Override
    public Prescription getPrescriptionById(long id) {
        return prescriptionDAO.getPrescriptionById(id);
    }

    @Override
    public Prescription addPrescription(Prescription prescription) {
        return prescriptionDAO.addPrescription(prescription);
    }

    @Override
    public Prescription updatePrescription(long id, Prescription prescription) {
        return prescriptionDAO.updatePrescription(id, prescription);
    }

    @Override
    public void deletePrescription(long id) {
        prescriptionDAO.deletePrescription(id);
    }
}
