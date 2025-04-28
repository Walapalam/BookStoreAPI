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
    private String orderId;
    private String customerId;
    private Map<String, Integer> items; // ISBN -> quantity
    private String orderDate;

    public Order(){}

    public Order(String orderId, String customerId, Map<String, Integer> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = Map.copyOf(items); // Immutable copy
        this.orderDate = LocalDateTime.now().toString();
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
