<%@ page import="java.util.List" %>
<%@ page import="com.sunbase.customer.model.Customers" %>
<%@ page import="com.sunbase.customer.dao.CustomerDAO" %>
<%@ page import="com.sunbase.customer.dao.impl.CustomerDAOImpl" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="home.css">
    <!-- Add Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
   
</head>
<body>

    <div class="navbar">
    
        <div class="navbar-left">
            <h2>Welcome to Home</h2>
        </div>  
        
        
        <div class="navbar-right">
            <a href="HomeServlet" class="logout-button">Go to Home</a>
        </div>
    </div>

    <h3>Updated List of Customers</h3>

    <table class="customer-table">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Street</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Email</th>
            <th>Phone</th>
            
        </tr>

        <!-- Retrieve the list of customers from the database -->
        <%
            // Retrieve the list of customers from the session
            List<Customers> currentCustomers=(List<Customers>) request.getAttribute("searchResults");
            
            if (currentCustomers != null && !currentCustomers.isEmpty()) {
                for (Customers customer : currentCustomers) {
        %>
                    <tr>
                        <td><%= customer.getId() %></td>
                        <td><%= customer.getFirst_name() %></td>
                        <td><%= customer.getLast_name() %></td>
                        <td><%= customer.getStreet() %></td>
                        <td><%= customer.getAddress() %></td>
                        <td><%= customer.getCity() %></td>
                        <td><%= customer.getState() %></td>
                        <td><%= customer.getEmail() %></td>
                        <td><%= customer.getPhone() %></td>
                    </tr>
        <% 
                }
            }

        %>
              
    </table>
</body>
</html>
