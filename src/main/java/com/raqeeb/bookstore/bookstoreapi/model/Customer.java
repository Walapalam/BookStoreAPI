/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.model;

import java.util.Objects;

/**
 *
 * @author Raqeeb
 */
public class Customer {
    private String customerID;
    private String name;
    private String email;
    private String password;

    public Customer(String customerID, String name, String email, String password) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public Customer(){}

    public String getCustomerID() {
        return customerID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     @Override
    public String toString() {
        return String.format("Customer{name='%s', email='%s', id='%s'}", name, email, customerID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Customer other = (Customer) obj;
        return Objects.equals(customerID, other.getCustomerID());
    }


    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }
}