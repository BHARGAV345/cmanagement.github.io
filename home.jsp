<%@ page import="java.util.List" %>
<%@ page import="com.sunbase.customer.model.Customers" %>
<%@ page import="com.sunbase.customer.dao.CustomerDAO" %>
<%@ page import="com.sunbase.customer.dao.impl.CustomerDAOImpl" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// Pagination Variables
	int totalCustomers=0;
	int pageSize = 5; // Number of Records per Page
	
	 List<Customers> customersList=(List<Customers>) request.getAttribute("searchResults");
	 Customers customerById = (Customers) request.getAttribute("customerById");
	 
	 if(customerById!=null){
		 totalCustomers=1;
	 }
	 else if(customersList.size()>0){
		 totalCustomers=customersList.size();
	 }
	int totalPages=(int)Math.ceil((double) totalCustomers / pageSize);
	int currentPage =request.getParameter("page") !=null ? Integer.parseInt(request.getParameter("page")) : 1;
	
	// Ensure currentPage is within valid bounds
	currentPage = Math.min(Math.max(currentPage, 1), totalPages);
	
	//Calculate start and end index of records for the current page
	int startIndex = (currentPage -1) * pageSize;
	int endIndex = Math.min(startIndex + pageSize, totalCustomers);		
	List<Customers>  currentCustomers =null;
	 if (customerById != null) {
	        // Handle customerById case
	    } else if (customersList != null) {
	        // Ensure startIndex is within bounds
	        if (startIndex >= 0 && startIndex < customersList.size()) {
	            // Get the sublist of customers for the current page 
	            currentCustomers = customersList.subList(startIndex, endIndex);
	        }
	    }
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="home.css">
    <!-- Add Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script>
        function confirmDelete(customerId) {
            var result = confirm("Are you sure you want to delete this customer?");
            if (result) {
                // If the user clicks OK, redirect to DeleteCustomerServlet with the customer ID
                window.location.href = "DeleteCustomerServlet?customerId=" + customerId;
            }
        }

        function toggleSearchBar() {
            var searchDropdown = document.getElementById("searchDropdown");
            var selectedOption = searchDropdown.options[searchDropdown.selectedIndex].value;
            var searchBar = document.getElementById("searchBar");

            if (selectedOption !== "none") {
                searchBar.style.display = "inline-block";
                searchBar.placeholder = "Enter " + selectedOption;
            } else {
                searchBar.style.display = "none";
            }
        }
        
        function search() {
            var searchDropdown = document.getElementById("searchDropdown");
            var selectedOption = searchDropdown.options[searchDropdown.selectedIndex].value;
            var searchBar = document.getElementById("searchBar");
            
            if (selectedOption !== "none" && searchBar.value.trim() !== "") {
                // If an option is selected and search bar is not empty, redirect to SearchServlet with the search value
                window.location.href = "SearchServlet?searchOption=" + selectedOption + "&searchValue=" + searchBar.value;
            }
        }
    </script>
</head>
<body>

    <%-- Check for success message parameter and display pop-up --%>
    <% String successMessage = request.getParameter("successMessage"); %>
    <% if (successMessage != null && !successMessage.isEmpty()) { %>
        <script>
            alert("<%= successMessage %>");
        </script>
    <% } %>

    <% String updateMessage =(String) request.getAttribute("updateMessage"); %>
    <% if (updateMessage != null && !updateMessage.isEmpty()) { %>
        <script>
            alert("<%= updateMessage %>");
        </script>
    <% } %>

    <% String errorMessage = (String)request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <script>
            alert("<%= errorMessage %>");
        </script>
    <% } %>

    <div class="navbar">
    
        <div class="navbar-left">
            <h2>Welcome to Home</h2>
        </div>  
        
        <div class="search-container">
            <label for="searchDropdown"></label>
            <select id="searchDropdown" onchange="toggleSearchBar()">
                <option value="none">Select Option</option>
                <option value="id">ID</option>
                <option value="firstName">First Name</option>
                <option value="email">Email</option>
                <option value="phone">Phone</option>
                <option value="city">City</option>
                <option value="state">State</option>
                <option value="all">All Customers</option>
            </select>
            <input type="text" id="searchBar" style="display: none;">
            <button class="search-icon" onclick="search()"><i class="fas fa-search"></i></button>
        </div>

        <div class="navbar-right">
            <a href="AddCustomerLoginVerifyServlet" class="add-button">Add Customer</a>
            <a href="LogoutServlet" class="logout-button">Logout</a>
        </div>
    </div>

    <h3>List of Customers</h3>

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
            <th colspan="2">Actions</th>
        </tr>

        <!-- Retrieve the list of customers from the database -->
        <%
            // Retrieve the list of customers from the session
            //List<Customers> customersList=(List<Customers>) request.getAttribute("searchResults");
            
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
                        <td>
                            <a href="EditCustomerServlet?customerId=<%= customer.getId() %>">
                                <i class="fas fa-edit action-icon edit-icon"></i>
                            </a>
                        </td>
                        <td>
                            <i class="fas fa-trash-alt action-icon delete-icon" onclick="confirmDelete(<%= customer.getId() %>)"></i>
                        </td>
                    </tr>
        <% 
                }
            }

            // Check if customerById is not null
           // Customers customerById = (Customers) request.getAttribute("customerById");
            if (customerById != null) {
        %>
                <tr>
                    <td><%= customerById.getId() %></td>
                    <td><%= customerById.getFirst_name() %></td>
                    <td><%= customerById.getLast_name() %></td>
                    <td><%= customerById.getStreet() %></td>
                    <td><%= customerById.getAddress() %></td>
                    <td><%= customerById.getCity() %></td>
                    <td><%= customerById.getState() %></td>
                    <td><%= customerById.getEmail() %></td>
                    <td><%= customerById.getPhone() %></td>
                    <td>
                        <a href="EditCustomerServlet?customerId=<%= customerById.getId() %>">
                            <i class="fas fa-edit action-icon edit-icon"></i>
                        </a>
                    </td>
                    <td>
                        <i class="fas fa-trash-alt action-icon delete-icon" onclick="confirmDelete(<%= customerById.getId() %>)"></i>
                    </td>
                </tr>
        <%
            }
        %>
    </table>

 <!-- Add pagination links -->
        <%if(totalPages > 1){%>
    	<div class="pagination">
    		<strong> Pages:</strong>
    		<%for (int pageLink =1; pageLink<=totalPages;pageLink++){%>
    			<a href="HomeServlet?page=<%=pageLink %>"><%= pageLink %></a>
   			<%} %>
   		</div>
   		<%} %>	
   
</body>
</html>
