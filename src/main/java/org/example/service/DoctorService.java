package org.example.service;

import org.example.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(long id);
    Doctor addDoctor(Doctor doctor);
    Doctor updateDoctor(long id, Doctor doctor);
    void deleteDoctor(long id);
}
