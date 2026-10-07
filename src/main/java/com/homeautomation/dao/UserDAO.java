package com.homeautomation.dao;

import com.homeautomation.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // =========================
    // LOGIN
    // =========================

    public int login(String username, String password) {

        String sql = "SELECT id FROM users " +
                     "WHERE username = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {

            System.out.println(
                    "LOGIN ERROR: " + e.getMessage()
            );

            e.printStackTrace();
        }

        return -1;
    }


    // =========================
    // REGISTER NEW USER
    // =========================

    public boolean register(String username, String password) {

        String sql =
                "INSERT INTO users (username, password) " +
                "VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println(
                    "REGISTER ERROR: " + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }
}