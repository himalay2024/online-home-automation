package com.homeautomation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class responsible for creating connections
 * between the application and the MySQL database.
 */
public class DBConnection {

    // MySQL database connection URL
    private static final String URL =
            "jdbc:mysql://localhost:3306/home_automation";

    // Database username
    private static final String USER = "root";

    // Database password used for the local development database
    private static final String PASSWORD = "02030202";

    /**
     * Creates and returns a connection to the MySQL database.
     *
     * @return active database connection
     * @throws SQLException if the driver cannot be loaded
     *         or the database connection fails
     */
    public static Connection getConnection() throws SQLException {

        try {
            // Load the MySQL JDBC driver before creating the connection
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            // Convert driver loading error into an SQLException
            throw new SQLException(
                    "MySQL JDBC Driver not found.", e);
        }

        // Create and return the database connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}