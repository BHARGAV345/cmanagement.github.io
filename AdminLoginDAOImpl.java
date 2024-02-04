package com.sunbase.customer.dao.impl;

import com.sunbase.customer.dao.AdminLoginDAO;
import com.sunbase.customer.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAOImpl implements AdminLoginDAO {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    // SQL Query for admin login validation
    private static final String VALIDATE_ADMIN_LOGIN_QUERY = "SELECT COUNT(*) FROM AdminLogin WHERE `username`=? AND `password`=?";

    // Constructor
    public AdminLoginDAOImpl() {
        try {
            // Create a new database connection
            connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateAdminLogin(String username, String password) {
        try {
            preparedStatement = connection.prepareStatement(VALIDATE_ADMIN_LOGIN_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // If count is greater than 0, the credentials are valid
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // Return false in case of failure
    }

    // Helper method to close resources
    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
