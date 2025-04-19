/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.repository;

import com.raqeeb.bookstore.bookstoreapi.model.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class OrderRepository {
    private static final OrderRepository instance = new OrderRepository();
    private final Map<String, List<Order>> orderStorage = new HashMap<>();

    private OrderRepository() {}

    public static OrderRepository getInstance() {
        return instance;
    }

    public void saveOrder(Order order) {
        orderStorage.computeIfAbsent(order.getCustomerId(), k -> new ArrayList<>()).add(order);
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderStorage.getOrDefault(customerId, Collections.emptyList());
    }
}
