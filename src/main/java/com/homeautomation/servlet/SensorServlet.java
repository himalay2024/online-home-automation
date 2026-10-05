package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sensor/save")
public class SensorServlet extends HttpServlet {

    private DeviceDAO deviceDAO;

    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int deviceId = Integer.parseInt(
                request.getParameter("deviceId")
        );

        double temperature = Double.parseDouble(
                request.getParameter("temperature")
        );

        double humidity = Double.parseDouble(
                request.getParameter("humidity")
        );

        boolean saved = deviceDAO.saveSensorReading(
                deviceId,
                temperature,
                humidity
        );

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (saved) {
            response.getWriter().println(
                    "<h2>Sensor reading saved successfully!</h2>"
            );
        } else {
            response.getWriter().println(
                    "<h2>Failed to save sensor reading.</h2>"
            );
        }
    }
}