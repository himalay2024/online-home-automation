package com.homeautomation.servlet;

import com.homeautomation.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles new user registration requests.
 *
 * This servlet receives registration details from the registration
 * form, validates the input and uses UserDAO to store the new user
 * in the database.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // DAO used to perform user registration in the database
    private UserDAO userDAO;

    /**
     * Initializes the servlet and creates the UserDAO object.
     */
    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    /**
     * Handles POST requests submitted from the registration form.
     *
     * @param request contains the username and password
     * @param response is used to display the registration result
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Read registration details submitted by the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // Validate that username and password are not empty
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            out.println("<h2>Registration Failed</h2>");
            out.println("<p>Please enter username and password.</p>");
            out.println(
                    "<a href='register.html'>Back to Registration</a>"
            );

            return;
        }

        // Remove unnecessary spaces from the username
        username = username.trim();

        // Save the new user using the DAO
        boolean registered =
                userDAO.register(username, password);

        if (registered) {

            // Display a successful registration page
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Registration Successful</title>");

            out.println("<style>");

            out.println("body {");
            out.println("font-family: Arial, sans-serif;");
            out.println("background: linear-gradient(135deg, #0f172a, #1e3a8a);");
            out.println("min-height: 100vh;");
            out.println("display: flex;");
            out.println("align-items: center;");
            out.println("justify-content: center;");
            out.println("margin: 0;");
            out.println("}");

            out.println(".box {");
            out.println("background: white;");
            out.println("padding: 40px;");
            out.println("border-radius: 18px;");
            out.println("text-align: center;");
            out.println("max-width: 400px;");
            out.println("width: 90%;");
            out.println("box-shadow: 0 20px 50px rgba(0,0,0,0.25);");
            out.println("}");

            out.println("h1 {");
            out.println("color: #16a34a;");
            out.println("}");

            out.println("p {");
            out.println("color: #64748b;");
            out.println("}");

            out.println("a {");
            out.println("display: inline-block;");
            out.println("margin-top: 15px;");
            out.println("padding: 12px 20px;");
            out.println("background: #2563eb;");
            out.println("color: white;");
            out.println("text-decoration: none;");
            out.println("border-radius: 8px;");
            out.println("font-weight: bold;");
            out.println("}");

            out.println("</style>");

            out.println("</head>");

            out.println("<body>");

            out.println("<div class='box'>");

            out.println("<h1>🎉 Account Created!</h1>");

            out.println(
                    "<p>Your account has been created successfully.</p>"
            );

            out.println(
                    "<a href='login.html'>Go to Login</a>"
            );

            out.println("</div>");

            out.println("</body>");

            out.println("</html>");

        } else {

            // Display an error when registration fails
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");

            out.println("<meta charset='UTF-8'>");

            out.println("<title>Registration Failed</title>");

            out.println("</head>");

            out.println("<body>");

            out.println("<h2>Registration Failed</h2>");

            out.println(
                    "<p>Username may already exist.</p>"
            );

            out.println(
                    "<a href='register.html'>Try Again</a>"
            );

            out.println("</body>");

            out.println("</html>");
        }
    }
}