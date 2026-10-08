package com.homeautomation.dao;

import com.homeautomation.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for user-related database operations.
 *
 * This class handles user login and registration using JDBC
 * and communicates directly with the users table in MySQL.
 */
public class UserDAO {

    /**
     * Checks the login credentials of a user.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return user ID if login is successful, otherwise -1
     */
    public int login(String username, String password) {

        String sql = "SELECT id FROM users " +
                     "WHERE username = ? AND password = ?";

        // Try-with-resources automatically closes JDBC resources
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Set user input as query parameters
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            // Return the user's ID when matching credentials are found
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {

            System.out.println(
                    "LOGIN ERROR: " + e.getMessage()
            );

            e.printStackTrace();
        }

        // -1 indicates that login was not successful
        return -1;
    }

    /**
     * Registers a new user in the database.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if registration is successful, otherwise false
     */
    public boolean register(String username, String password) {

        String sql =
                "INSERT INTO users (username, password) " +
                "VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Set registration details as query parameters
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