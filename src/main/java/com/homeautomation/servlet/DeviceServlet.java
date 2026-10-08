package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;
import com.homeautomation.model.Device;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Handles requests for adding a new home automation device.
 *
 * This servlet receives device details from the web form,
 * validates the user's session and stores the device using DeviceDAO.
 */
@WebServlet("/device/save")
public class DeviceServlet extends HttpServlet {

    // DAO used to perform device database operations
    private DeviceDAO deviceDAO;

    /**
     * Initializes the servlet and creates the DeviceDAO object.
     */
    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    /**
     * Handles the POST request for saving a new device.
     *
     * @param request contains the device name and type
     * @param response is used to send the result to the browser
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Read device details submitted from the form
        String name = request.getParameter("name");
        String type = request.getParameter("type");

        // Create a Device object and set its initial values
        Device device = new Device();

        device.setName(name);
        device.setType(type);

        // New devices are initially switched OFF
        device.setStatus(false);

        // Get the existing user session without creating a new session
        HttpSession session = request.getSession(false);

        // Allow device creation only for logged-in users
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.html");
            return;
        }

        // Retrieve the logged-in user's ID from the session
        int userId = (Integer) session.getAttribute("userId");

        // Save the device and associate it with the logged-in user
        boolean saved = deviceDAO.saveDevice(device, userId);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Display the result of the database operation
        if (saved) {
            response.getWriter().println(
                    "<h2>Device saved successfully!</h2>");
        } else {
            response.getWriter().println(
                    "<h2>Failed to save device.</h2>");
        }
    }
}