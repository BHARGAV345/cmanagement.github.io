<%@ page import="com.sunbase.customer.model.Customers" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <!-- Add your CSS links or styles here if needed -->
    <link rel="stylesheet" type="text/css" href="addCustomer.css">
    
</head>
<body>

<%
    Customers customer = (Customers) request.getAttribute("customer");

    if (customer != null) {
%>
<div class="box-container">
    <h2>Edit Customer</h2>

    <form action="UpdateCustomerServlet" method="post">
    <div class="left-card">
        <!-- Hidden input to store the customer ID -->
        <input type="hidden" name="id" value="<%= customer.getId() %>" />

        <!-- Form fields -->
        <div>
	        <label for="first_name">First Name:</label>
	        <input type="text" id="first_name" name="first_name" value="<%= customer.getFirst_name() %>" required><br>
		</div>
		
		<div>
	        <label for="last_name">Last Name:</label>
	        <input type="text" id="last_name" name="last_name" value="<%= customer.getLast_name() %>" required><br>
		</div>
		
		<div>
	        <label for="street">Street:</label>
	        <input type="text" id="street" name="street" value="<%= customer.getStreet() %>" required><br>
        </div>
        
		<div>
	        <label for="address">Address:</label>
	        <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required><br>
		</div>
    </div>
	
	<div class="right-card">
		<div>
	        <label for="city">City:</label>
	        <input type="text" id="city" name="city" value="<%= customer.getCity() %>" required><br>
		</div>
		
		<div>
	        <label for="state">State:</label>
	        <input type="text" id="state" name="state" value="<%= customer.getState() %>" required><br>
		</div>
		
		<div>
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required><br>
        </div>
		
		<div>
	        <label for="phone">Phone:</label>
	        <input type="tel" id="phone" name="phone" value="<%= customer.getPhone() %>" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"  required><br>
	        <small>Format: 123-456-7890</small>
		</div>
    </div>  
		 <div class="down-center">
        	<input type="submit" value="Update">
        </div>	
    </form>
    </div>
<%
    }
%>

</body>
</html>
