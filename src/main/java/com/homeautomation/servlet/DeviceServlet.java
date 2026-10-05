package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;
import com.homeautomation.model.Device;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/device/save")
public class DeviceServlet extends HttpServlet {

    private DeviceDAO deviceDAO;

    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String type = request.getParameter("type");

        Device device = new Device();

        device.setName(name);
        device.setType(type);
        device.setStatus(false);

        // Demo user ID = 1
        int userId = 1;

        boolean saved = deviceDAO.saveDevice(device, userId);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (saved) {
            response.getWriter().println(
                    "<h2>Device saved successfully!</h2>"
            );
        } else {
            response.getWriter().println(
                    "<h2>Failed to save device.</h2>"
            );
        }
    }
}