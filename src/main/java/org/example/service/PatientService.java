package org.example.service;

import org.example.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(long id);
    Patient addPatient(Patient patient);
    Patient updatePatient(long id, Patient patient);
    void deletePatient(long id);
}
