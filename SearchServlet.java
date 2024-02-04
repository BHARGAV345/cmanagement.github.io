package com.sunbase.customer.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.dao.impl.CustomerDAOImpl;
import com.sunbase.customer.model.Customers;
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        // Initialize CustomerDAOImpl in the init method
        customerDAO = new CustomerDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract search option and value from request parameters
        String searchOption = request.getParameter("searchOption");
        String searchValue = request.getParameter("searchValue");

        // Initialize a list to store search results
        List<Customers> searchResults = null;
       Customers customerById = null;

        // Check the search option and call the appropriate method in CustomerDAOImpl
        switch (searchOption) {
            case "id":
                // Call method to get customers by ID
            	int id = Integer.parseInt(searchValue);
            	customerById =  customerDAO.getCustomerById(id);
                break;
            case "firstName":
                // Call method to get customers by First Name
                searchResults = customerDAO.getCustomersByFirstName(searchValue);
                break;
            case "email":
                // Call method to get customers by Email
                searchResults = customerDAO.getCustomersByEmail(searchValue);
                break;
            case "phone":
                // Call method to get customers by Phone
                searchResults = customerDAO.getCustomersByPhone(searchValue);
                break;
            case "city":
                // Call method to get customers by City
                searchResults = customerDAO.getCustomersByCity(searchValue);
                break;
            case "state":
                // Call method to get customers by State
                searchResults = customerDAO.getCustomersByState(searchValue);
                break;
            case "all":
                // Call method to get customers by State
                searchResults = customerDAO.getAllCustomers();
                break;    
            default:
                // Handle invalid search option
                break;
        }
        
        if(searchResults == null && customerById == null)
        {
        	// Set the message for no results found
            request.setAttribute("noResultsMessage", "No customers found. Please try a different search.");

            // Forward the request to noCustomer.jsp
            RequestDispatcher noCustomerDispatcher = request.getRequestDispatcher("noCustomer.jsp");
            noCustomerDispatcher.forward(request, response);
        }
        else
        {
        	// Set the search results in the request attribute
        	request.setAttribute("searchResults", searchResults);
        	
        	request.setAttribute("customerById", customerById);
        	
        	// Forward the request to home.jsp
        	RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        	dispatcher.forward(request, response);
        	
        }
    }
}
