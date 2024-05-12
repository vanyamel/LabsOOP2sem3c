package org.example.dao;
import org.springframework.stereotype.Repository;

import org.example.model.Patient;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PatientDAO {

    private static final String SELECT_ALL_PATIENTS = "SELECT * FROM patient";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
    private static final String INSERT_PATIENT = "INSERT INTO patient (name, doctor_id, discharged) VALUES (?, ?, ?)";
    private static final String UPDATE_PATIENT = "UPDATE patient SET name = ?, doctor_id = ?, discharged = ? WHERE id = ?";
    private static final String DELETE_PATIENT = "DELETE FROM patient WHERE id = ?";

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PATIENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setName(resultSet.getString("name"));
                patient.setDoctorId(resultSet.getInt("doctor_id"));
                patient.setDischarged(resultSet.getBoolean("discharged"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public Patient getPatientById(long id) {
        Patient patient = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    patient = new Patient();
                    patient.setId(resultSet.getInt("id"));
                    patient.setName(resultSet.getString("name"));
                    patient.setDoctorId(resultSet.getInt("doctor_id"));
                    patient.setDischarged(resultSet.getBoolean("discharged"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public Patient addPatient(Patient patient) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setLong(2, patient.getDoctorId());
            preparedStatement.setBoolean(3, patient.isDischarged());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating patient failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public Patient updatePatient(long id, Patient patient) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setLong(2, patient.getDoctorId());
            preparedStatement.setBoolean(3, patient.isDischarged());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public void deletePatient(long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PATIENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
