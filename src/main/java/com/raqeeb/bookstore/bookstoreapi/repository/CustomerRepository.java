/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.repository;

import com.raqeeb.bookstore.bookstoreapi.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class CustomerRepository {
    private static final CustomerRepository instance = new CustomerRepository();
    
    private final Map<String, Customer> customerStorage = new HashMap<>();

    private CustomerRepository() {
        initialize();
    }

    private void initialize() {
        Customer customer1 = new Customer("CUST001", "John Doe", "john@example.com", "password123");
        Customer customer2 = new Customer("CUST002", "Jane Smith", "jane@example.com", "password456");
        Customer customer3 = new Customer("CUST003", "Bob Wilson", "bob@example.com", "password789");

        customerStorage.put(customer1.getCustomerID(), customer1);
        customerStorage.put(customer2.getCustomerID(), customer2);
        customerStorage.put(customer3.getCustomerID(), customer3);
    }
    
    public static CustomerRepository getInstance(){
        return instance;
    }
    
    public Customer getCustomer(String customerID){
        return customerStorage.get(customerID);
    }
    
    public void addCustomer(Customer customer){
        customerStorage.put(customer.getCustomerID(), customer);
    }
    
    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customerStorage.values());
    }
    
    public boolean deleteCustomer(String customerID){
        return customerStorage.remove(customerID) != null;
    }
    
    public boolean updateCustomer(String customerID, Customer updateCustomer){
        if (customerStorage.containsKey(customerID)){
            customerStorage.put(customerID, updateCustomer);
            return true;
        }
        return false;
    }
}
