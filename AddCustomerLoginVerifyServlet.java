package com.sunbase.customer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddCustomerLoginVerifyServlet")
public class AddCustomerLoginVerifyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminUsername = (String)session.getAttribute("adminUsername");

        if (adminUsername != null && !adminUsername.isEmpty()) {
            // Admin is logged in, redirect to addCustomer.jsp
            response.sendRedirect("addCustomer.jsp");
        } else {
            // Admin is not logged in, redirect to index.jsp
            response.sendRedirect("index.jsp");
        }
    }
}
