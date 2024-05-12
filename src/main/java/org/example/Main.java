package org.example;

import org.example.dao.DoctorDAO;
import org.example.model.Doctor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctors = doctorDAO.getAllDoctors();

        for (Doctor doctor : doctors) {
            System.out.println(doctor.getName() + " - " + doctor.getSpecialization());
        }
    }
}
