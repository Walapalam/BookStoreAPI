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

    // Matches OrderService.addOrder
    public void addOrder(Order order) {
        orderStorage.computeIfAbsent(order.getCustomerId(), k -> new ArrayList<>()).add(order);
    }

    // Matches OrderService.getAllOrdersByCustomerId
    public List<Order> getAllOrdersByCustomerId(String customerId) {
        return orderStorage.getOrDefault(customerId, Collections.emptyList());
    }

    // Matches OrderService.getOrder
    public Order getOrder(String orderId) {
        for (List<Order> orders : orderStorage.values()) {
            for (Order order : orders) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
        }
        return null;
    }
}
