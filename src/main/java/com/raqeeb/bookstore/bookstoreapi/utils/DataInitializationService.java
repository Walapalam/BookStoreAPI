/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.utils;

import com.raqeeb.bookstore.bookstoreapi.model.*;
import com.raqeeb.bookstore.bookstoreapi.repository.*;

import java.time.Year;
import java.util.HashMap;

import com.raqeeb.bookstore.bookstoreapi.model.Author;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import com.raqeeb.bookstore.bookstoreapi.repository.AuthorRepository;

import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class DataInitializationService {
    private static final AuthorRepository authorRepo = AuthorRepository.getInstance();
    private static final BookRepository bookRepo = BookRepository.getInstance();
    private static final CustomerRepository customerRepo = CustomerRepository.getInstance();
    private static final CartRepository cartRepo = CartRepository.getInstance();
    private static final OrderRepository orderRepo = OrderRepository.getInstance();

    public static void initializeData() {
        // Initialize Authors and Books (existing code)
        Author author1 = new Author("AUTH001", "George Orwell", "English novelist known for '1984' and 'Animal Farm'");
        Author author2 = new Author("AUTH002", "J.R.R. Tolkien", "English writer and philologist, author of 'The Lord of the Rings'");
        Author author3 = new Author("AUTH003", "Agatha Christie", "British crime novelist, known for detective fiction");

        authorRepo.addAuthor(author1);
        authorRepo.addAuthor(author2);
        authorRepo.addAuthor(author3);

        Book book1 = new Book("1984", author1, "ISBN001", Year.of(1949), 15.99, 50);
        Book book2 = new Book("Animal Farm", author1, "ISBN002", Year.of(1945), 12.99, 45);
        Book book3 = new Book("The Hobbit", author2, "ISBN003", Year.of(1937), 19.99, 60);
        Book book4 = new Book("Murder on the Orient Express", author3, "ISBN004", Year.of(1934), 14.99, 40);

        bookRepo.addBook(book1);
        bookRepo.addBook(book2);
        bookRepo.addBook(book3);
        bookRepo.addBook(book4);

        // Initialize Customers
        Customer customer1 = new Customer("CUST001", "John Doe", "john@example.com", "password123");
        Customer customer2 = new Customer("CUST002", "Jane Smith", "jane@example.com", "password456");
        Customer customer3 = new Customer("CUST003", "Bob Wilson", "bob@example.com", "password789");

        customerRepo.addCustomer(customer1);
        customerRepo.addCustomer(customer2);
        customerRepo.addCustomer(customer3);

        // Initialize Carts
        Cart cart1 = new Cart(customer1.getCustomerID());
        cart1.addItem("ISBN001", 2);
        cart1.addItem("ISBN002", 1);

        Cart cart2 = new Cart(customer2.getCustomerID());
        cart2.addItem("ISBN003", 1);
        cart2.addItem("ISBN004", 2);

        cartRepo.addCart(cart1);
        cartRepo.addCart(cart2);

        // Initialize Orders
        Map<String, Integer> orderItems1 = new HashMap<>();
        orderItems1.put("ISBN001", 1);
        orderItems1.put("ISBN002", 2);
        Order order1 = new Order("ORD001", customer1.getCustomerID(), orderItems1);

        Map<String, Integer> orderItems2 = new HashMap<>();
        orderItems2.put("ISBN003", 1);
        orderItems2.put("ISBN004", 1);
        Order order2 = new Order("ORD002", customer2.getCustomerID(), orderItems2);

        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
    }
}
