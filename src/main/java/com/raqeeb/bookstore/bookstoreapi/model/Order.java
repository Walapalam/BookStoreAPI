/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class Order {
    private final String orderId;
    private final String customerId;
    private final Map<String, Integer> items; // ISBN -> quantity
    private final LocalDateTime orderDate;

    public Order(String orderId, String customerId, Map<String, Integer> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = Map.copyOf(items); // Immutable copy
        this.orderDate = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setCustomerId(String customerId) {
    }
}
