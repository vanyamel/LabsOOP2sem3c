package org.example.dao;
import org.springframework.stereotype.Repository;

import org.example.model.Prescription;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PrescriptionDAO {

    private static final String SELECT_ALL_PRESCRIPTIONS = "SELECT * FROM prescription";
    private static final String SELECT_PRESCRIPTION_BY_ID = "SELECT * FROM prescription WHERE id = ?";
    private static final String INSERT_PRESCRIPTION = "INSERT INTO prescription (doctor_id, patient_id, nurse_id, diagnosis_id, description) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRESCRIPTION = "UPDATE prescription SET doctor_id = ?, patient_id = ?, nurse_id = ?, diagnosis_id = ?, description = ? WHERE id = ?";
    private static final String DELETE_PRESCRIPTION = "DELETE FROM prescription WHERE id = ?";

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRESCRIPTIONS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Prescription prescription = new Prescription();
                prescription.setId(resultSet.getInt("id"));
                prescription.setDoctorId(resultSet.getInt("doctor_id"));
                prescription.setPatientId(resultSet.getInt("patient_id"));
                prescription.setNurseId(resultSet.getInt("nurse_id"));
                prescription.setDiagnosisId(resultSet.getInt("diagnosis_id"));
                prescription.setDescription(resultSet.getString("description"));
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }

    public Prescription getPrescriptionById(long id) {
        Prescription prescription = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRESCRIPTION_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    prescription = new Prescription();
                    prescription.setId(resultSet.getInt("id"));
                    prescription.setDoctorId(resultSet.getInt("doctor_id"));
                    prescription.setPatientId(resultSet.getInt("patient_id"));
                    prescription.setNurseId(resultSet.getInt("nurse_id"));
                    prescription.setDiagnosisId(resultSet.getInt("diagnosis_id"));
                    prescription.setDescription(resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescription;
    }

    public Prescription addPrescription(Prescription prescription) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRESCRIPTION, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, prescription.getDoctorId());
            preparedStatement.setLong(2, prescription.getPatientId());
            preparedStatement.setLong(3, prescription.getNurseId());
            preparedStatement.setLong(4, prescription.getDiagnosisId());
            preparedStatement.setString(5, prescription.getDescription());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating prescription failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    prescription.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating prescription failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescription;
    }

    public Prescription updatePrescription(long id, Prescription prescription) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION)) {
            preparedStatement.setLong(1, prescription.getDoctorId());
            preparedStatement.setLong(2, prescription.getPatientId());
            preparedStatement.setLong(3, prescription.getNurseId());
            preparedStatement.setLong(4, prescription.getDiagnosisId());
            preparedStatement.setString(5, prescription.getDescription());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescription;
    }

    public void deletePrescription(long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRESCRIPTION)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
