package com.homeautomation.servlet;

import com.homeautomation.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Handles user login requests.
 *
 * This servlet receives login credentials from the login page,
 * verifies them using UserDAO and creates a session for
 * successfully authenticated users.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // DAO object used to perform user-related database operations
    private UserDAO userDAO;

    /**
     * Initializes the servlet and creates the UserDAO object.
     */
    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    /**
     * Handles POST requests submitted from the login form.
     *
     * @param request contains the username and password
     * @param response is used to send the result or redirect the user
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Read login credentials submitted by the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Verify the credentials using the DAO
        int userId = userDAO.login(username, password);

        if (userId != -1) {

            // Create a session for the authenticated user
            HttpSession session = request.getSession();

            // Store user information in the session
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            // Redirect the user to the device dashboard
            response.sendRedirect(
                    request.getContextPath() + "/devices"
            );

        } else {

            // Display an error message when login credentials are invalid
            response.setContentType("text/html");
            response.getWriter().println(
                    "<h2>Invalid username or password.</h2>"
            );
        }
    }
}