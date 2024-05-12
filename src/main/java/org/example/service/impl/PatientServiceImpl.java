package org.example.service.impl;

import org.example.dao.PatientDAO;
import org.example.model.Patient;
import org.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Override
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    public Patient getPatientById(long id) {
        return patientDAO.getPatientById(id);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientDAO.addPatient(patient);
    }

    @Override
    public Patient updatePatient(long id, Patient patient) {
        return patientDAO.updatePatient(id, patient);
    }

    @Override
    public void deletePatient(long id) {
        patientDAO.deletePatient(id);
    }
}