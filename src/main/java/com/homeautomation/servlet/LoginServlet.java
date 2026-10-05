package com.homeautomation.servlet;

import com.homeautomation.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        int userId = userDAO.login(username, password);

        if (userId != -1) {

            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            response.sendRedirect(
                    request.getContextPath() + "/devices"
            );

        } else {

            response.setContentType("text/html");
            response.getWriter().println(
                    "<h2>Invalid username or password.</h2>"
            );
        }
    }
}