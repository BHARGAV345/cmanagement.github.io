<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customer</title>
    <link rel="stylesheet" type="text/css" href="addCustomer.css">
</head>
<body>

  <div class="box-container">
    <h2>Add Customer</h2>

    <form action="AddCustomerServlet" method="post">
    <div class="left-card">
        <div>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" placeholder="First Name" required>
        </div>

        <div>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" placeholder="Last Name"  required>
        </div>

        <div>
            <label for="street">Street:</label>
            <input type="text" id="street" name="street"  placeholder="Street"  required>
        </div>

        <div>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address"  placeholder="Address"  required>
        </div>
     </div>

	 <div class="right-card">
        <div>
            <label for="city">City:</label>
            <input type="text" id="city" name="city" placeholder="City"  required>
        </div>

        <div>
            <label for="state">State:</label>
            <input type="text" id="state" name="state" placeholder="State"  required>
        </div>

        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Email"  required>
        </div>

        <div>
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" placeholder="123-456-7890" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required><br>
            <small>Format: 123-456-7890</small>
        </div>
	</div>
        <div class="down-center">
            <input type="submit" value="Add">
        </div>
    </form>
  </div> 
    
    

</body>
</html>
