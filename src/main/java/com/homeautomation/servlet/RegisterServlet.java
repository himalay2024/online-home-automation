package com.homeautomation.servlet;

import com.homeautomation.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // Empty field check
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            out.println("<h2>Registration Failed</h2>");
            out.println("<p>Please enter username and password.</p>");
            out.println(
                    "<a href='register.html'>Back to Registration</a>"
            );

            return;
        }

        username = username.trim();

        boolean registered =
                userDAO.register(username, password);

        if (registered) {

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