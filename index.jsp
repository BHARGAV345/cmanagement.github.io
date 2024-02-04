<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="adminLogin.css">
</head>
<body>

    <div class="card"> <!-- Add a container with the "card" class -->
        <h2>Admin Login</h2>

        <%-- Check for signInError attribute and display error message --%>
        <% String signInError = (String) request.getAttribute("errorMessage"); %>
        <% if (signInError != null && !signInError.isEmpty()) { %>
            <p style="color: red;"><%= signInError %></p>
        <% } %>

        <form class="login-form" action="AdminLoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <input type="submit" value="Login">
        </form>
    </div>

</body>
</html>
