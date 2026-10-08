package com.homeautomation.dao;

import com.homeautomation.model.Device;
import com.homeautomation.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for performing database operations
 * related to home automation devices.
 *
 * This class uses JDBC to save, retrieve and update device data,
 * device history and sensor readings in the MySQL database.
 */
public class DeviceDAO {

    /**
     * Saves a new device for a specific user.
     *
     * @param device the device to be stored
     * @param userId the ID of the user who owns the device
     * @return true if the device is saved successfully, otherwise false
     */
    public boolean saveDevice(Device device, int userId) {

        String sql = "INSERT INTO devices (user_id, name, type, status) VALUES (?, ?, ?, ?)";

        // Try-with-resources automatically closes JDBC resources
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, device.getName());
            statement.setString(3, device.getType());
            statement.setBoolean(4, device.isStatus());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("DEVICE SAVE ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all devices belonging to a particular user.
     *
     * @param userId the ID of the user
     * @return list of devices owned by the user
     */
    public List<Device> getAllDevices(int userId) {

        List<Device> devices = new ArrayList<>();

        String sql = "SELECT id, name, type, status FROM devices WHERE user_id = ?";

        // Fetch devices using a parameterized SQL query
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            // Convert each database row into a Device object
            while (resultSet.next()) {

                Device device = new Device();

                device.setId(resultSet.getInt("id"));
                device.setName(resultSet.getString("name"));
                device.setType(resultSet.getString("type"));
                device.setStatus(resultSet.getBoolean("status"));

                devices.add(device);
            }

        } catch (SQLException e) {
            System.out.println("DEVICE FETCH ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return devices;
    }

    /**
     * Updates the ON/OFF status of a device.
     *
     * @param deviceId the ID of the device
     * @param status the new status of the device
     * @return true if the status is updated successfully, otherwise false
     */
    public boolean updateDeviceStatus(int deviceId, boolean status) {

        String sql = "UPDATE devices SET status = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, status);
            statement.setInt(2, deviceId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("DEVICE STATUS UPDATE ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Stores a device ON/OFF action in the device history table.
     *
     * @param deviceId the ID of the device
     * @param action the action performed on the device
     * @return true if the history is saved successfully, otherwise false
     */
    public boolean saveDeviceHistory(int deviceId, String action) {

        String sql = "INSERT INTO device_history (device_id, action) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, deviceId);
            statement.setString(2, action);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("HISTORY SAVE ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Stores temperature and humidity readings for a device.
     *
     * @param deviceId the ID of the device
     * @param temperature recorded temperature value
     * @param humidity recorded humidity value
     * @return true if the sensor reading is saved successfully, otherwise false
     */
    public boolean saveSensorReading(int deviceId,
            double temperature,
            double humidity) {

        String sql = "INSERT INTO sensor_readings " +
                "(device_id, temperature, humidity) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, deviceId);
            statement.setDouble(2, temperature);
            statement.setDouble(3, humidity);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("SENSOR SAVE ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves device activity history for a specific user.
     *
     * The query joins the devices and device_history tables
     * so that the device name can be displayed with each action.
     *
     * @param userId the ID of the user
     * @return list containing device name, action and action time
     */
    public List<String[]> getDeviceHistory(int userId) {

        List<String[]> history = new ArrayList<>();

        String sql = "SELECT d.name, h.action, h.action_time " +
                "FROM device_history h " +
                "JOIN devices d ON h.device_id = d.id " +
                "WHERE d.user_id = ? " +
                "ORDER BY h.action_time DESC";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            // Store each history record as a String array
            while (resultSet.next()) {

                String deviceName = resultSet.getString("name");

                String action = resultSet.getString("action");

                String actionTime = resultSet.getString("action_time");

                history.add(new String[] {
                        deviceName,
                        action,
                        actionTime
                });
            }

        } catch (SQLException e) {

            System.out.println(
                    "HISTORY FETCH ERROR: " + e.getMessage());

            e.printStackTrace();
        }

        return history;
    }
}