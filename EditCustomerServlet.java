package com.sunbase.customer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.dao.impl.CustomerDAOImpl;
import com.sunbase.customer.model.Customers;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() throws ServletException {
        // Initialize CustomerDAO in the init method
        customerDAO = new CustomerDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminUsername = (String) session.getAttribute("adminUsername");

        // Check if admin is logged in
        if (adminUsername != null && !adminUsername.isEmpty()) {
            // Admin is logged in, proceed with editing
        	
            // Get the customer ID from the request parameter
            int customerId = Integer.parseInt(request.getParameter("customerId"));

            // Get the customer details by ID
            Customers customer = customerDAO.getCustomerById(customerId);

            // Set the customer details as an attribute in the request
            request.setAttribute("customer", customer);

            // Forward the request to editCustomer.jsp
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        } else {
            // Admin is not logged in, redirect to index.jsp
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
