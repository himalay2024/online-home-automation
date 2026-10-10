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
import java.io.PrintWriter;
import java.util.List;

/**
 * Displays all devices belonging to the currently logged-in user.
 *
 * This servlet retrieves device data using DeviceDAO and dynamically
 * generates the device dashboard as an HTML response.
 */
@WebServlet("/devices")
public class DeviceListServlet extends HttpServlet {

    // DAO used to retrieve device information from the database
    private DeviceDAO deviceDAO;

    /**
     * Initializes the servlet and creates the DeviceDAO object.
     */
    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    /**
     * Handles GET requests for the device dashboard.
     *
     * @param request contains the current user session
     * @param response is used to generate the device dashboard
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get the existing session without creating a new session
        HttpSession session = request.getSession(false);

        // Only logged-in users can access the device dashboard
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.html"
            );
            return;
        }

        // Retrieve logged-in user's information from the session
        int userId = (Integer) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

        List<Device> devices;

        try {
            // Fetch all devices belonging to the logged-in user
            devices = deviceDAO.getAllDevices(userId);

        } catch (Exception e) {

            // Display an error if device retrieval fails
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<h2>Unable to load devices</h2>");
            out.println("<p>" + e.getMessage() + "</p>");

            return;
        }

        int totalDevices = devices.size();
        int devicesOn = 0;
        for (Device device : devices) {
            if (device.isStatus()) {
                devicesOn++;
            }
        }
        int devicesOff = totalDevices - devicesOn;

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // Start generating the HTML dashboard
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println(
                "<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>"
        );

        out.println("<title>My Home Devices</title>");

        // =========================
        // CSS STYLING
        // =========================

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: #f1f5f9;");
        out.println("color: #1e293b;");
        out.println("}");

        // Header
        out.println(".header {");
        out.println("background: linear-gradient(135deg, #0f172a, #1e3a8a);");
        out.println("color: white;");
        out.println("padding: 18px 30px;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("flex-wrap: wrap;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".header h1 {");
        out.println("margin: 0;");
        out.println("font-size: 24px;");
        out.println("}");

        out.println(".user-area {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".logout {");
        out.println("background: #ef4444;");
        out.println("color: white;");
        out.println("padding: 9px 16px;");
        out.println("border-radius: 7px;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        // Main container
        out.println(".container {");
        out.println("max-width: 1100px;");
        out.println("margin: 35px auto;");
        out.println("padding: 0 20px;");
        out.println("}");

        out.println(".welcome {");
        out.println("background: white;");
        out.println("border-radius: 14px;");
        out.println("padding: 25px;");
        out.println("margin-bottom: 25px;");
        out.println("box-shadow: 0 4px 15px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".welcome h2 {");
        out.println("margin-top: 0;");
        out.println("}");

        // Dashboard action buttons
        out.println(".action-buttons {");
        out.println("display: flex;");
        out.println("gap: 12px;");
        out.println("flex-wrap: wrap;");
        out.println("margin-bottom: 25px;");
        out.println("}");

        out.println(".action-button {");
        out.println("text-decoration: none;");
        out.println("color: white;");
        out.println("padding: 11px 18px;");
        out.println("border-radius: 8px;");
        out.println("font-weight: bold;");
        out.println("display: inline-block;");
        out.println("}");

        out.println(".add-button {");
        out.println("background: #16a34a;");
        out.println("}");

        out.println(".sensor-button {");
        out.println("background: #2563eb;");
        out.println("}");

        out.println(".history-button {");
        out.println("background: #7c3aed;");
        out.println("}");

        // Device grid
        out.println(".stats-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(160px, 1fr)); gap: 16px; margin: 24px 0; }");
                out.println(".weather-card { background: linear-gradient(135deg, #eff6ff, #ffffff); }");
        out.println(".weather-card small { display: block; margin-top: 12px; color: #64748b; }");
        out.println(".status { display: inline-flex; align-items: center; gap: 6px; padding: 7px 13px; border-radius: 20px; font-size: 13px; font-weight: 700; margin: 12px 0; }");
        out.println(".status-on { color: #166534; background: #dcfce7; border: 1px solid #86efac; }");
        out.println(".status-off { color: #991b1b; background: #fee2e2; border: 1px solid #fca5a5; }");
out.println(".stat-card { background: #ffffff; border-radius: 14px; padding: 22px; box-shadow: 0 4px 14px rgba(0,0,0,0.08); border: 1px solid #e5e7eb; }");
        out.println(".stat-card h3 { margin: 0 0 10px; font-size: 15px; color: #555; }");
        out.println(".stat-card p { margin: 0; font-size: 30px; font-weight: bold; color: #2563eb; }");
        out.println(".device-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));");
        out.println("gap: 20px;");
        out.println("}");

        // Device card
        out.println(".device-card {");
        out.println("background: white;");
        out.println("border-radius: 14px;");
        out.println("padding: 22px;");
        out.println("box-shadow: 0 4px 15px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".device-icon {");
        out.println("font-size: 42px;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".device-card h3 {");
        out.println("margin: 5px 0;");
        out.println("font-size: 21px;");
        out.println("}");

        out.println(".device-type {");
        out.println("color: #64748b;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".status {");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 18px;");
        out.println("}");

        out.println(".status-on {");
        out.println("color: #16a34a;");
        out.println("}");

        out.println(".status-off {");
        out.println("color: #dc2626;");
        out.println("}");

        out.println(".control-buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("}");

        out.println(".control-buttons form {");
        out.println("flex: 1;");
        out.println("}");

        out.println(".control-button {");
        out.println("width: 100%;");
        out.println("border: none;");
        out.println("padding: 11px;");
        out.println("border-radius: 7px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".on-button {");
        out.println("background: #22c55e;");
        out.println("color: white;");
        out.println("}");

        out.println(".off-button {");
        out.println("background: #ef4444;");
        out.println("color: white;");
        out.println("}");

        // Empty state
        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 40px;");
        out.println("border-radius: 14px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 4px 15px rgba(0,0,0,0.08);");
        out.println("}");

        // Footer
        out.println(".footer {");
        out.println("text-align: center;");
        out.println("padding: 30px;");
        out.println("color: #64748b;");
        out.println("}");

        // Responsive layout for smaller screens
        out.println("@media (max-width: 600px) {");

        out.println(".header {");
        out.println("padding: 15px;");
        out.println("}");

        out.println(".header h1 {");
        out.println("font-size: 20px;");
        out.println("}");

        out.println(".container {");
        out.println("margin: 20px auto;");
        out.println("padding: 0 12px;");
        out.println("}");

        out.println(".action-button {");
        out.println("width: 100%;");
        out.println("text-align: center;");
        out.println("}");

        out.println(".action-buttons {");
        out.println("display: block;");
        out.println("}");

        out.println(".action-button {");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println("}");

        out.println("</style>");
        out.println("</head>");

        // =========================
        // PAGE BODY
        // =========================

        out.println("<body>");

        // Header containing application name and user information
        out.println("<div class='header'>");

        out.println("<h1>🏠 Home Automation</h1>");

        out.println("<div class='user-area'>");

        out.println(
                "<span>Welcome, " +
                (username == null ? "User" : username) +
                "</span>"
        );

        out.println(
                "<a class='logout' href='" +
                request.getContextPath() +
                "/logout'>Logout</a>"
        );

        out.println("</div>");
        out.println("</div>");

        // Main dashboard container
        out.println("<div class='container'>");

        // Welcome section
        out.println("<div class='welcome'>");

        out.println("<h2>My Home Devices</h2>");

        out.println(
                "<p>Manage and monitor your connected home devices.</p>"
        );

        out.println("</div>");

        // =========================
        // ACTION BUTTONS
        // =========================

        out.println("<div class='action-buttons'>");

        // Link to add a new device
        out.println(
                "<a class='action-button add-button' href='" +
                request.getContextPath() +
                "/devices.html'>" +
                "➕ Add Device</a>"
        );

        // Link to sensor monitoring
        out.println(
                "<a class='action-button sensor-button' href='" +
                request.getContextPath() +
                "/sensor.html'>" +
                "🌡 Sensor Monitoring</a>"
        );

        // Link to device history
        out.println(
                "<a class='action-button history-button' href='" +
                request.getContextPath() +
                "/history'>" +
                "📜 Device History</a>"
        );

        out.println("</div>");

        // =========================
        // DEVICE DISPLAY
        // =========================

        // Show an empty-state message when no devices exist
        if (devices == null || devices.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>📭 No Devices Found</h2>");

            out.println(
                    "<p>You haven't added any devices yet.</p>"
            );

            out.println(
                    "<a class='action-button add-button' href='" +
                    request.getContextPath() +
                    "/devices.html'>" +
                    "➕ Add Your First Device</a>"
            );

            out.println("</div>");

        } else {

            // Create a responsive grid for all available devices
            out.println("<div class='stats-grid'>");
            out.println("<div class='stat-card'><h3>Total Devices</h3><p>" + totalDevices + "</p></div>");
            out.println("<div class='stat-card'><h3>Devices ON</h3><p>" + devicesOn + "</p></div>");
            out.println("<div class='stat-card'><h3>Devices OFF</h3><p>" + devicesOff + "</p></div>");
            out.println("</div>");
            out.println("<div class='stat-card weather-card'><h3>🌦️ Delhi Live Weather</h3><div id='weatherIcon' style='font-size:36px;margin:10px 0'>🌤️</div><p id='delhiTemp'>Loading...</p><div id='delhiHumidity'>Humidity: Loading...</div><small id='weatherUpdated'>Fetching current weather...</small></div>");
        out.println("<script>");
        out.println("async function loadDelhiWeather(){try{const r=await fetch('https://api.open-meteo.com/v1/forecast?latitude=28.6139&longitude=77.2090&current=temperature_2m,relative_humidity_2m,weather_code&timezone=Asia%2FKolkata');if(!r.ok)throw new Error('Weather request failed');const d=await r.json();const c=d.current.weather_code;const icon=c===0?'☀️':([1,2,3].includes(c)?'🌤️':([45,48].includes(c)?'🌫️':([51,53,55,61,63,65,80,81,82].includes(c)?'🌧️':([71,73,75,85,86].includes(c)?'❄️':'⛈️'))));document.getElementById('weatherIcon').textContent=icon;document.getElementById('delhiTemp').textContent=d.current.temperature_2m+' °C';document.getElementById('delhiHumidity').textContent='Humidity: '+d.current.relative_humidity_2m+'%';document.getElementById('weatherUpdated').textContent='Updated: '+d.current.time.replace('T',' ');}catch(e){document.getElementById('delhiTemp').textContent='Unavailable';document.getElementById('delhiHumidity').textContent='Weather data could not be loaded';document.getElementById('weatherUpdated').textContent='Please refresh later';}}");
        out.println("loadDelhiWeather();setInterval(loadDelhiWeather,300000);");
        out.println("</script>");
        out.println("<div class='device-grid'>");

            // Iterate through the device collection
            for (Device device : devices) {

                // Select an icon according to the device type
                String icon = "💡";

                if (device.getType() != null) {

                    if (device.getType().equalsIgnoreCase("Fan")) {
                        icon = "🌀";

                    } else if (
                            device.getType().equalsIgnoreCase(
                                    "Air Conditioner"
                            )
                    ) {
                        icon = "❄️";
                    }
                }

                out.println("<div class='device-card'>");

                out.println(
                        "<div class='device-icon'>" +
                        icon +
                        "</div>"
                );

                out.println(
                        "<h3>" +
                        device.getName() +
                        "</h3>"
                );

                out.println(
                        "<div class='device-type'>" +
                        device.getType() +
                        "</div>"
                );

                // Display the current device status
                if (device.isStatus()) {

                    out.println(
                            "<div class='status status-on'>" +
                            "● Device is ON" +
                            "</div>"
                    );

                } else {

                    out.println(
                            "<div class='status status-off'>" +
                            "● Device is OFF" +
                            "</div>"
                    );
                }

                // =========================
                // DEVICE CONTROL
                // =========================

                out.println("<div class='control-buttons'>");

                // Form for switching the device ON
                out.println(
                        "<form action='" +
                        request.getContextPath() +
                        "/device/control' method='post'>"
                );

                out.println(
                        "<input type='hidden' name='deviceId' value='" +
                        device.getId() +
                        "'>"
                );

                out.println(
                        "<input type='hidden' name='action' value='ON'>"
                );

                out.println(
                        "<button class='control-button on-button' " +
                        "type='submit'>ON</button>"
                );

                out.println("</form>");

                // Form for switching the device OFF
                out.println(
                        "<form action='" +
                        request.getContextPath() +
                        "/device/control' method='post'>"
                );

                out.println(
                        "<input type='hidden' name='deviceId' value='" +
                        device.getId() +
                        "'>"
                );

                out.println(
                        "<input type='hidden' name='action' value='OFF'>"
                );

                out.println(
                        "<button class='control-button off-button' " +
                        "type='submit'>OFF</button>"
                );

                out.println("</form>");

                out.println("</div>");
                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</div>");

        // Footer
        out.println("<div class='footer'>");

        out.println(
                "Online Home Automation Monitoring System"
        );

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}