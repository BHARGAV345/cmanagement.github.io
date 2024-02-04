package com.sunbase.customer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/CustomerManagement";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "##Qwerty8444";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Open a connection to the database
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Database driver not found.");
            }
        }
        return connection;
    }

    // Close the connection to the database
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
