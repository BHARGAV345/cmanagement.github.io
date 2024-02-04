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

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        // Initialize CustomerDAO in the init method
        customerDAO = new CustomerDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        // Fetch the list of customers
        List<Customers> customersList = customerDAO.getAllCustomers();

        // Set the list of customers as an attribute in the request
        request.setAttribute("searchResults", customersList);

        // Forward to home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
