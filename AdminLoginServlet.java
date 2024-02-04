package com.sunbase.customer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sunbase.customer.dao.AdminLoginDAO;
import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.dao.impl.AdminLoginDAOImpl;
import com.sunbase.customer.dao.impl.CustomerDAOImpl;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private AdminLoginDAO adminLoginDAO;
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        // Initialize AdminLoginDAO and CustomerDAO in the init method
        adminLoginDAO = new AdminLoginDAOImpl();
        customerDAO = new CustomerDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get login details from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate admin login
        boolean isValidLogin = adminLoginDAO.validateAdminLogin(username, password);

        if (isValidLogin) {
            // If login is successful, add admin to session
            HttpSession session = request.getSession();
            session.setAttribute("adminUsername", username);

            // Redirect to HomeServlet
            response.sendRedirect("HomeServlet");
        } else {
            // If login fails, display error message on index.jsp
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
