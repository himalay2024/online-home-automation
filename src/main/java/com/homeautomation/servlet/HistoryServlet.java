package com.homeautomation.servlet;

import com.homeautomation.dao.DeviceDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Displays the activity history of the user's home automation devices.
 *
 * This servlet retrieves device history from the database using DeviceDAO
 * and generates an HTML page containing device names, actions and
 * activity timestamps.
 */
@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    // DAO used to retrieve device history from the database
    private DeviceDAO deviceDAO;

    /**
     * Initializes the servlet and creates the DeviceDAO object.
     */
    @Override
    public void init() {
        deviceDAO = new DeviceDAO();
    }

    /**
     * Handles GET requests for viewing device history.
     *
     * @param request contains the current user's session information
     * @param response is used to generate the history page
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the logged-in user's ID from the session
        Integer userId =
                (Integer) request.getSession().getAttribute("userId");

        // Redirect users to login if they are not authenticated
        if (userId == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.html");
            return;
        }

        // Fetch device history belonging to the logged-in user
        List<String[]> history =
                deviceDAO.getDeviceHistory(userId);

        // Configure the HTML response
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // Start generating the history page
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");

        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>Device History</title>");

        // CSS styles for the history page
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

        out.println(".back {");
        out.println("text-decoration: none;");
        out.println("background: #2563eb;");
        out.println("color: white;");
        out.println("padding: 9px 16px;");
        out.println("border-radius: 8px;");
        out.println("font-size: 14px;");
        out.println("}");

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1000px;");
        out.println("margin: 40px auto;");
        out.println("}");

        out.println(".intro {");
        out.println("margin-bottom: 25px;");
        out.println("}");

        out.println(".intro h2 {");
        out.println("font-size: 28px;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".intro p {");
        out.println("color: #64748b;");
        out.println("}");

        out.println(".history-card {");
        out.println("background: white;");
        out.println("border-radius: 16px;");
        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");
        out.println("overflow: hidden;");
        out.println("}");

        out.println("table {");
        out.println("width: 100%;");
        out.println("border-collapse: collapse;");
        out.println("}");

        out.println("th {");
        out.println("background: #f8fafc;");
        out.println("text-align: left;");
        out.println("padding: 16px;");
        out.println("color: #334155;");
        out.println("}");

        out.println("td {");
        out.println("padding: 16px;");
        out.println("border-top: 1px solid #e2e8f0;");
        out.println("}");

        out.println(".on {");
        out.println("color: #16a34a;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".off {");
        out.println("color: #64748b;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".empty {");
        out.println("padding: 35px;");
        out.println("text-align: center;");
        out.println("color: #64748b;");
        out.println("}");

        // Responsive styling for smaller screens
        out.println("@media (max-width: 600px) {");

        out.println(".header {");
        out.println("flex-direction: column;");
        out.println("gap: 15px;");
        out.println("text-align: center;");
        out.println("}");

        out.println(".history-card {");
        out.println("overflow-x: auto;");
        out.println("}");

        out.println("table {");
        out.println("min-width: 600px;");
        out.println("}");

        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        // Page header and dashboard navigation
        out.println("<header class='header'>");

        out.println("<h1>🏠 Home Automation</h1>");

        out.println("<a class='back' href='" +
                request.getContextPath() +
                "/devices'>← Dashboard</a>");

        out.println("</header>");

        out.println("<main class='container'>");

        // Page introduction
        out.println("<div class='intro'>");
        out.println("<h2>📋 Device History</h2>");
        out.println("<p>View recent ON and OFF activity of your devices.</p>");
        out.println("</div>");

        out.println("<div class='history-card'>");

        // Display a message when there is no device activity
        if (history.isEmpty()) {

            out.println("<div class='empty'>");
            out.println("<h3>No history available</h3>");
            out.println("<p>Device activity will appear here.</p>");
            out.println("</div>");

        } else {

            // Create a table for displaying device history
            out.println("<table>");

            out.println("<tr>");
            out.println("<th>Device</th>");
            out.println("<th>Action</th>");
            out.println("<th>Date & Time</th>");
            out.println("</tr>");

            // Loop through all history records
            for (String[] record : history) {

                // Apply different CSS classes for ON and OFF actions
                String actionClass =
                        "ON".equalsIgnoreCase(record[1])
                                ? "on"
                                : "off";

                out.println("<tr>");

                // Display the device name
                out.println("<td>" +
                        record[0] +
                        "</td>");

                // Display the device action
                out.println("<td class='" +
                        actionClass +
                        "'>" +
                        record[1] +
                        "</td>");

                // Display the date and time of the action
                out.println("<td>" +
                        record[2] +
                        "</td>");

                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</div>");

        out.println("</main>");

        out.println("</body>");
        out.println("</html>");
    }
}