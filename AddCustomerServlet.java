package com.sunbase.customer.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.dao.impl.CustomerDAOImpl;
import com.sunbase.customer.model.Customers;

@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get customer details from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Parse phone as an integer
       

        // Create a Customers object with the collected details
        Customers newCustomer = new Customers(firstName, lastName, street, address, city, state, email, phone);

        // Create an instance of CustomerDAO
        CustomerDAO customerDAO = new CustomerDAOImpl();

        // Try to add the new customer
        int rowsAffected = customerDAO.addCustomer(newCustomer);

        // Call getAllCustomers to update the customer list
        List<Customers> updatedCustomersList = customerDAO.getAllCustomers();

        // Set the updated customer list in the request
        request.setAttribute("searchResults", updatedCustomersList);

        // Redirect to home.jsp with a pop-up message based on the result
        if (rowsAffected > 0) {
            // Customer added successfully, redirect to home.jsp with success message
            //response.sendRedirect("home.jsp?successMessage=Customer added successfully");
            request.getRequestDispatcher("home.jsp?successMessage=Customer added successfully").forward(request, response);
        } else {
            // Customer not added, redirect back to addcustomer.jsp with details pre-filled
            response.sendRedirect("addcustomer.jsp?success=false&firstName=" + firstName +
                    "&lastName=" + lastName +
                    "&street=" + street +
                    "&address=" + address +
                    "&city=" + city +
                    "&state=" + state +
                    "&email=" + email +
                    "&phone=" + phone);
        }
    }
}
