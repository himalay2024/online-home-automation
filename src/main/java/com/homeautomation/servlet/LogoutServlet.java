package com.homeautomation.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Handles user logout requests.
 *
 * This servlet invalidates the current user session and redirects
 * the user back to the login page.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Handles GET requests used for logging out the user.
     *
     * @param request contains the current user session
     * @param response is used to redirect the user to the login page
     * @throws IOException if the redirect operation fails
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        // Get the existing session without creating a new one
        HttpSession session = request.getSession(false);

        if (session != null) {

            // Invalidate the session to log the user out
            session.invalidate();
        }

        // Redirect the user to the login page
        response.sendRedirect(
                request.getContextPath() + "/login.html"
        );
    }
}