/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class Cart {
    private String customerId;
    private final Map<String, Integer> items = new HashMap<>(); // Key: ISBN, Value: Quantity

    public Cart(String customerId) {
        this.customerId = customerId;
    }

    public Cart(){}
    
    public String getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItem(String isbn, int quantity) {
        items.put(isbn, items.getOrDefault(isbn, 0) + quantity);
    }

    public void updateItemQuantity(String isbn, int quantity) {
        if (items.containsKey(isbn)) {
            items.put(isbn, quantity);
        }
    }

    public void removeItem(String isbn) {
        items.remove(isbn);
    }

    public void clearCart() {
        items.clear();
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
