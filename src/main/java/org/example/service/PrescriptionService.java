package org.example.service;

import org.example.model.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions();
    Prescription getPrescriptionById(long id);
    Prescription addPrescription(Prescription prescription);
    Prescription updatePrescription(long id, Prescription prescription);
    void deletePrescription(long id);
}
