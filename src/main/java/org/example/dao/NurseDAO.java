package org.example.dao;

import org.example.model.Nurse;
import org.example.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class NurseDAO {

    private static final String SELECT_ALL_NURSES = "SELECT * FROM nurse";
    private static final String SELECT_NURSE_BY_ID = "SELECT * FROM nurse WHERE id = ?";
    private static final String INSERT_NURSE = "INSERT INTO nurse (name, specialization) VALUES (?, ?)";
    private static final String UPDATE_NURSE = "UPDATE nurse SET name = ?, specialization = ? WHERE id = ?";
    private static final String DELETE_NURSE = "DELETE FROM nurse WHERE id = ?";

    public List<Nurse> getAllNurses() {
        List<Nurse> nurses = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NURSES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Nurse nurse = new Nurse();
                nurse.setId(resultSet.getInt("id"));
                nurse.setName(resultSet.getString("name"));
                nurse.setSpecialization(resultSet.getString("specialization"));
                nurses.add(nurse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }

    public Nurse getNurseById(long id) {
        Nurse nurse = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NURSE_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    nurse = new Nurse();
                    nurse.setId(resultSet.getInt("id"));
                    nurse.setName(resultSet.getString("name"));
                    nurse.setSpecialization(resultSet.getString("specialization"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    public Nurse addNurse(Nurse nurse) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NURSE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nurse.getName());
            preparedStatement.setString(2, nurse.getSpecialization());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating nurse failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    nurse.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating nurse failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    public Nurse updateNurse(long id, Nurse nurse) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NURSE)) {
            preparedStatement.setString(1, nurse.getName());
            preparedStatement.setString(2, nurse.getSpecialization());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    public void deleteNurse(long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NURSE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
