package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handles sensor reading requests for home automation devices.
 *
 * This servlet receives temperature and humidity values from the
 * web interface and stores them in the database using DeviceDAO.
 */
@WebServlet("/sensor/save")
public class SensorServlet extends HttpServlet {

    // DAO used to store sensor readings in the database
    private DeviceDAO deviceDAO;

    /**
     * Initializes the servlet and creates the DeviceDAO object.
     */
    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    /**
     * Handles POST requests containing sensor readings.
     *
     * @param request contains device ID, temperature and humidity values
     * @param response is used to display the result of the save operation
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Read the device ID from the submitted sensor form
        int deviceId = Integer.parseInt(
                request.getParameter("deviceId")
        );

        // Read and convert the temperature value
        double temperature = Double.parseDouble(
                request.getParameter("temperature")
        );

        // Read and convert the humidity value
        double humidity = Double.parseDouble(
                request.getParameter("humidity")
        );

        // Save the sensor reading using the DAO
        boolean saved = deviceDAO.saveSensorReading(
                deviceId,
                temperature,
                humidity
        );

        // Set the response type and character encoding
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (saved) {

            // Inform the user when the reading is saved successfully
            response.getWriter().println(
                    "<h2>Sensor reading saved successfully!</h2>"
            );

        } else {

            // Display an error message when saving fails
            response.getWriter().println(
                    "<h2>Failed to save sensor reading.</h2>"
            );
        }
    }
}