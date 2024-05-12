package org.example.dao;

import org.example.model.Doctor;
import org.example.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DoctorDAO {

    private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctor";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE id = ?";
    private static final String INSERT_DOCTOR = "INSERT INTO doctor (name, specialization) VALUES (?, ?)";
    private static final String UPDATE_DOCTOR = "UPDATE doctor SET name = ?, specialization = ? WHERE id = ?";
    private static final String DELETE_DOCTOR = "DELETE FROM doctor WHERE id = ?";

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DOCTORS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt("id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public Doctor getDoctorById(long id) {
        Doctor doctor = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    doctor = new Doctor();
                    doctor.setId(resultSet.getInt("id"));
                    doctor.setName(resultSet.getString("name"));
                    doctor.setSpecialization(resultSet.getString("specialization"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public Doctor addDoctor(Doctor doctor) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialization());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating doctor failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    doctor.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating doctor failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public Doctor updateDoctor(long id, Doctor doctor) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialization());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public void deleteDoctor(long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCTOR)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
