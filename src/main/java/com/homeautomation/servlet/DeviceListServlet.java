package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;
import com.homeautomation.model.Device;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/devices")
public class DeviceListServlet extends HttpServlet {

    private DeviceDAO deviceDAO;

    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.html");
            return;
        }

        String username = (String) request.getSession().getAttribute("username");

        List<Device> devices = deviceDAO.getAllDevices(userId);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");

        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>Home Automation Dashboard</title>");

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("margin: 0;");
        out.println("padding: 0;");
        out.println("font-family: Arial, sans-serif;");
        out.println("}");

        out.println("body {");
        out.println("min-height: 100vh;");
        out.println("background: #f1f5f9;");
        out.println("color: #0f172a;");
        out.println("}");

        out.println(".header {");
        out.println("background: #0f172a;");
        out.println("color: white;");
        out.println("padding: 22px 6%;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("}");

        out.println(".header h1 {");
        out.println("font-size: 24px;");
        out.println("}");

        out.println(".user-area {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".logout {");
        out.println("text-decoration: none;");
        out.println("background: #ef4444;");
        out.println("color: white;");
        out.println("padding: 9px 16px;");
        out.println("border-radius: 8px;");
        out.println("font-size: 14px;");
        out.println("}");

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 40px auto;");
        out.println("}");

        out.println(".welcome {");
        out.println("margin-bottom: 25px;");
        out.println("}");

        out.println(".welcome h2 {");
        out.println("font-size: 28px;");
        out.println("margin-bottom: 7px;");
        out.println("}");

        out.println(".welcome p {");
        out.println("color: #64748b;");
        out.println("}");

        out.println(".device-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));");
        out.println("gap: 22px;");
        out.println("}");

        out.println(".device-card {");
        out.println("background: white;");
        out.println("border-radius: 16px;");
        out.println("padding: 25px;");
        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".device-icon {");
        out.println("font-size: 35px;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".device-card h3 {");
        out.println("font-size: 20px;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".type {");
        out.println("color: #64748b;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".status {");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println(".on {");
        out.println("color: #16a34a;");
        out.println("}");

        out.println(".off {");
        out.println("color: #64748b;");
        out.println("}");

        out.println(".buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("}");

        out.println(".buttons button {");
        out.println("flex: 1;");
        out.println("border: none;");
        out.println("padding: 11px;");
        out.println("border-radius: 8px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".on-button {");
        out.println("background: #22c55e;");
        out.println("color: white;");
        out.println("}");

        out.println(".off-button {");
        out.println("background: #e2e8f0;");
        out.println("color: #334155;");
        out.println("}");

        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 15px;");
        out.println("text-align: center;");
        out.println("color: #64748b;");
        out.println("}");

        out.println("@media (max-width: 600px) {");
        out.println(".header {");
        out.println("flex-direction: column;");
        out.println("gap: 15px;");
        out.println("text-align: center;");
        out.println("}");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        out.println("<header class='header'>");

        out.println("<h1>🏠 Home Automation</h1>");

        out.println("<div class='user-area'>");
        out.println("<span>Welcome, " +
                (username != null ? username : "User") +
                "</span>");

        out.println("<a class='logout' href='" +
                request.getContextPath() +
                "/logout'>Logout</a>");

        out.println("</div>");

        out.println("</header>");

        out.println("<main class='container'>");

        out.println("<div class='welcome'>");
        out.println("<h2>My Home Devices</h2>");
        out.println("<p>Monitor and control your connected devices.</p>");
        out.println("</div>");
        out.println("<div style='display:flex; gap:12px; flex-wrap:wrap; margin-bottom:25px;'>");

        out.println("<a href='" +
                request.getContextPath() +
                "/sensor.html' " +
                "style='text-decoration:none; background:#2563eb; color:white; " +
                "padding:11px 18px; border-radius:8px; font-weight:bold;'>"
                + "🌡️ Sensor Monitoring</a>");

        out.println("<a href='" +
                request.getContextPath() +
                "/history' " +
                "style='text-decoration:none; background:#475569; color:white; " +
                "padding:11px 18px; border-radius:8px; font-weight:bold;'>"
                + "📋 Device History</a>");

        out.println("</div>");

        if (devices.isEmpty()) {

            out.println("<div class='empty'>");
            out.println("<h3>No devices found</h3>");
            out.println("<p>Add a device to start monitoring your home.</p>");
            out.println("</div>");

        } else {

            out.println("<div class='device-grid'>");

            for (Device device : devices) {

                String icon = "💡";

                if ("Fan".equalsIgnoreCase(device.getType())) {
                    icon = "🌀";
                } else if ("Air Conditioner"
                        .equalsIgnoreCase(device.getType())) {
                    icon = "❄️";
                }

                out.println("<div class='device-card'>");

                out.println("<div class='device-icon'>" +
                        icon + "</div>");

                out.println("<h3>" +
                        device.getName() +
                        "</h3>");

                out.println("<p class='type'>Type: " +
                        device.getType() +
                        "</p>");

                String statusClass = device.isStatus() ? "on" : "off";

                String statusText = device.isStatus() ? "● ON" : "● OFF";

                out.println("<p class='status " +
                        statusClass +
                        "'>Status: " +
                        statusText +
                        "</p>");

                out.println("<form action='" +
                        request.getContextPath() +
                        "/device/control' method='post'>");

                out.println("<input type='hidden' " +
                        "name='id' value='" +
                        device.getId() + "'>");

                out.println("<div class='buttons'>");

                out.println("<button class='on-button' " +
                        "type='submit' name='action' value='ON'>" +
                        "Turn ON</button>");

                out.println("<button class='off-button' " +
                        "type='submit' name='action' value='OFF'>" +
                        "Turn OFF</button>");

                out.println("</div>");

                out.println("</form>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }
}