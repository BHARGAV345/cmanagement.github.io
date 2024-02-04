package com.sunbase.customer.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.dao.impl.CustomerDAOImpl;
import com.sunbase.customer.model.Customers;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerDAO customerDAO;

    public void init() throws ServletException {
        // Initialize CustomerDAO in the init method
        customerDAO = new CustomerDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminUsername = (String)session.getAttribute("adminUsername");

        // Check if admin is logged in
        if (adminUsername != null && !adminUsername.isEmpty()) {
            // Admin is logged in, proceed with deletion

            // Get the customer ID from the request
            int customerId = Integer.parseInt(request.getParameter("customerId"));

            // Delete the customer using CustomerDAO
            int rowsAffected = customerDAO.deleteCustomer(customerId);

            // Get the updated customer list
            List<Customers> updatedCustomersList = customerDAO.getAllCustomers();

            // Set the updated customer list in the request
            request.setAttribute("searchResults", updatedCustomersList);

            // Redirect to the appropriate page based on deletion success
            if (rowsAffected > 0) {
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                response.sendRedirect("deletionFailed.jsp");
            }
        } else {
            // Admin is not logged in, redirect to index.jsp
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
