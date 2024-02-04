package com.sunbase.customer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        // Remove admin from the session
        session.removeAttribute("adminUsername");

        // Invalidate the session
        session.invalidate();

        // Forward the request to logout.jsp
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
}
