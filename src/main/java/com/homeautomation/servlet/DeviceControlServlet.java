package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handles ON/OFF control requests for home automation devices.
 *
 * This servlet receives the device ID and requested action from
 * the dashboard, updates the device status in the database and
 * records the action in the device history.
 */
@WebServlet("/device/control")
public class DeviceControlServlet extends HttpServlet {

    // DAO used to update device status and save device history
    private DeviceDAO deviceDAO;

    /**
     * Initializes the servlet and creates the DeviceDAO object.
     */
    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    /**
     * Handles POST requests sent when the user switches a device
     * ON or OFF.
     *
     * @param request contains device ID and requested action
     * @param response is used to redirect the user back to the dashboard
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Read the device ID and requested action from the form
        String idParameter = request.getParameter("id");
        String action = request.getParameter("action");

        // Convert the device ID from String to integer
        int deviceId = Integer.parseInt(idParameter);

        // ON means true and any other action means OFF
        boolean status = "ON".equalsIgnoreCase(action);

        // Update the current status of the device in the database
        boolean updated = deviceDAO.updateDeviceStatus(deviceId, status);

        // Store the ON/OFF action in the device history
        deviceDAO.saveDeviceHistory(deviceId, action.toUpperCase());

        if (updated) {

            // Return to the device dashboard after successful update
            response.sendRedirect(
                    request.getContextPath() + "/devices"
            );

        } else {

            // Display an error message if the status update fails
            response.getWriter().println(
                    "<h2>Failed to update device status.</h2>"
            );
        }
    }
}