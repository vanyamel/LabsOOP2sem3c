package org.example.service.impl;

import org.example.dao.DoctorDAO;
import org.example.model.Doctor;
import org.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    @Override
    public Doctor getDoctorById(long id) {
        return doctorDAO.getDoctorById(id);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorDAO.addDoctor(doctor);
    }

    @Override
    public Doctor updateDoctor(long id, Doctor doctor) {
        return doctorDAO.updateDoctor(id, doctor);
    }

    @Override
    public void deleteDoctor(long id) {
        doctorDAO.deleteDoctor(id);
    }
}
