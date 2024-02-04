package com.sunbase.customer.model;

public class AdminLogin {
    private int id;
    private String password;

    // Default constructor
    public AdminLogin() {}

    // Parameterized constructor
    public AdminLogin(int id, String password) {
	//this keyword used to over come the shadowing problem.
        this.id = id;
        this.password = password;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
