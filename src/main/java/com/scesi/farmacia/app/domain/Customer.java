package com.scesi.farmacia.app.domain;

public class Customer {
    private String name;
    private String lastName;
    private String email;
    private int ID;

    public Customer(String name, String lastName, String email, int ID) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
