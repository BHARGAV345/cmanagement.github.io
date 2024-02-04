package com.sunbase.customer.servlets;

import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.dao.impl.CustomerDAOImpl;
import com.sunbase.customer.model.Customers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form parameters
            int id = Integer.parseInt(request.getParameter("id"));
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            // Create a Customers object with updated details
            Customers updatedCustomer = new Customers(id, first_name, last_name, street, address, city, state, email, phone);

            // Update the customer in the database
            int rowsAffected = customerDAO.updateCustomer(updatedCustomer);

            if (rowsAffected > 0) {
                // Update successful
                request.setAttribute("updateMessage", "Update successful");
            } else {
                // Update failed
                request.setAttribute("errorMessage", "Update failed");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle the case where parsing the customer ID fails
            request.setAttribute("errorMessage", "Invalid customer ID");
        }

        // Get the updated customer list outside the if-else block
        List<Customers> updatedCustomersList = customerDAO.getAllCustomers();

        // Set the updated customer list in the request
        request.setAttribute("searchResults", updatedCustomersList);

        // Redirect to allCustomers.jsp
        request.getRequestDispatcher("allCustomers.jsp").forward(request, response);
    }
}
