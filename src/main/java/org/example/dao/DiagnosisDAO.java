package org.example.dao;

import org.example.model.Diagnosis;
import org.example.util.DatabaseConnection;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DiagnosisDAO {

    private static final String SELECT_ALL_DIAGNOSES = "SELECT * FROM diagnosis";
    private static final String SELECT_DIAGNOSIS_BY_ID = "SELECT * FROM diagnosis WHERE id = ?";
    private static final String INSERT_DIAGNOSIS = "INSERT INTO diagnosis (name, description) VALUES (?, ?)";
    private static final String UPDATE_DIAGNOSIS = "UPDATE diagnosis SET name = ?, description = ? WHERE id = ?";
    private static final String DELETE_DIAGNOSIS = "DELETE FROM diagnosis WHERE id = ?";

    public List<Diagnosis> getAllDiagnoses() {
        List<Diagnosis> diagnoses = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIAGNOSES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setId(resultSet.getInt("id"));
                diagnosis.setName(resultSet.getString("name"));
                diagnosis.setDescription(resultSet.getString("description"));
                diagnoses.add(diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnoses;
    }

    public Diagnosis getDiagnosisById(long id) {
        Diagnosis diagnosis = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIAGNOSIS_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    diagnosis = new Diagnosis();
                    diagnosis.setId(resultSet.getInt("id"));
                    diagnosis.setName(resultSet.getString("name"));
                    diagnosis.setDescription(resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosis;
    }

    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIAGNOSIS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, diagnosis.getName());
            preparedStatement.setString(2, diagnosis.getDescription());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating diagnosis failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    diagnosis.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating diagnosis failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosis;
    }

    public Diagnosis updateDiagnosis(long id, Diagnosis diagnosis) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIAGNOSIS)) {
            preparedStatement.setString(1, diagnosis.getName());
            preparedStatement.setString(2, diagnosis.getDescription());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosis;
    }

    public void deleteDiagnosis(long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DIAGNOSIS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
