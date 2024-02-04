package com.sunbase.customer.dao;

import java.util.List;

import com.sunbase.customer.model.Customers;

public interface CustomerDAO {

    // Add a new customer
    int addCustomer(Customers customer);

    // Update an existing customer
    int updateCustomer(Customers customer);
    
    // Get a customer by ID
    Customers getCustomerById(int customerId);

    // Get all customers
    List<Customers> getAllCustomers();
    
    //Get Customer by firstName
    List<Customers> getCustomersByFirstName(String firstName);
    
    //Get Customer by email
    List<Customers> getCustomersByEmail(String email);
    
    //Get Customer by phone
    List<Customers> getCustomersByPhone(String phone);
    
    //Get Customer by city
    List<Customers> getCustomersByCity(String city);
    
    //Get Customer by state
    List<Customers> getCustomersByState(String state);

    // Delete a customer
    int deleteCustomer(int customerId);
    
    
    
   
}
