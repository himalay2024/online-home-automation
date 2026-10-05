package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/device/control")
public class DeviceControlServlet extends HttpServlet {

    private DeviceDAO deviceDAO;

    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter = request.getParameter("id");
        String action = request.getParameter("action");

        int deviceId = Integer.parseInt(idParameter);

        boolean status = "ON".equalsIgnoreCase(action);

        boolean updated = deviceDAO.updateDeviceStatus(deviceId, status);
        deviceDAO.saveDeviceHistory(deviceId, action.toUpperCase());

        if (updated) {
            response.sendRedirect(
                    request.getContextPath() + "/devices");
        } else {
            response.getWriter().println(
                    "<h2>Failed to update device status.</h2>");
        }
    }
}