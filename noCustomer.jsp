<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>No Customer Found</title>
    <link rel="stylesheet" type="text/css" href="noCustomer.css">
</head>
<body>

    <div class="card-container">
        <h2>No Customer Found</h2>

        <p><%= request.getAttribute("noResultsMessage") %></p>

        <div class="button-container">
            <a href="HomeServlet" class="btn">Go Back to Home</a>
        </div>
    </div>

</body>
</html>
