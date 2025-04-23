/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.service;

import com.raqeeb.bookstore.bookstoreapi.exception.CustomerNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.model.Customer;
import com.raqeeb.bookstore.bookstoreapi.repository.CustomerRepository;
import java.util.List;

/**
 *
 * @author Raqeeb
 */
public class CustomerService {

    private static final CustomerService instance = new CustomerService();
    private final CustomerRepository customerRepository;

    private CustomerService() {
        this.customerRepository = CustomerRepository.getInstance();
    }

    public static CustomerService getInstance() {
        return instance;
    }

    public Customer createCustomer(Customer customer) throws InvalidInputException {
        if (customer.getName() == null || customer.getEmail() == null) {
            throw new InvalidInputException("Customer name or email cannot be null");
        }
        customerRepository.addCustomer(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomerById(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return customer;
    }

    public boolean updateCustomer(String customerId, Customer updatedCustomer) throws InvalidInputException {
        if (updatedCustomer.getName() == null || updatedCustomer.getEmail() == null) {
            throw new InvalidInputException("Updated customer name or email cannot be null");
        }
        return customerRepository.updateCustomer(customerId, updatedCustomer);
    }

    public boolean deleteCustomer(String customerId) throws CustomerNotFoundException {
        if (!customerRepository.deleteCustomer(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return true;
    }

    public boolean customerExists(String customerId) {
        try {
            getCustomerById(customerId);
            return true;
        } catch (CustomerNotFoundException e) {
            return false;
        }
    }
}