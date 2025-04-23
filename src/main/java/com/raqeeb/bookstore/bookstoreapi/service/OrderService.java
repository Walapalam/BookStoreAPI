/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.service;

import com.raqeeb.bookstore.bookstoreapi.exception.CartNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.exception.OrderNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.model.Cart;
import com.raqeeb.bookstore.bookstoreapi.model.Order;
import com.raqeeb.bookstore.bookstoreapi.repository.OrderRepository;
import java.util.List;

/**
 *
 * @author Raqeeb
 */
public class OrderService {

    private static final OrderService instance = new OrderService();
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private static final CustomerService customerService = CustomerService.getInstance();

    private OrderService() {
        this.orderRepository = OrderRepository.getInstance();
        this.cartService = CartService.getInstance();
    }

    public static OrderService getInstance() {
        return instance;
    }

    public Order createOrder(String orderID, String customerID) throws CartNotFoundException, InvalidInputException {
        // Fetch the cart and create order from it
        Cart cart = cartService.getCartByCustomerId(customerID);
        if (cart.getItems().isEmpty()) {
            throw new InvalidInputException("Cannot create order, cart is empty.");
        }
        Order order = new Order(orderID, customerID, cart.getItems());
        orderRepository.addOrder(order);
        return order;
    }

    public List<Order> getAllOrders(String customerID) {
        return orderRepository.getAllOrdersByCustomerId(customerID);
    }

    public Order getOrderById(String customerID, String orderId) throws InvalidInputException {
        Order order = orderRepository.getOrder(orderId);
        if (order == null || !order.getCustomerId().equals(customerID)) {
            throw new InvalidInputException("Order not found for customer " + customerID);
        }
        return order;
    }

    public List<Order> getOrdersByCustomer(String customerId) throws InvalidInputException {
        if (!customerService.customerExists(customerId)) {
            throw new InvalidInputException("Customer not found: " + customerId);
        }
        return orderRepository.getAllOrdersByCustomerId(customerId);
    }


    public Order updateOrderStatus(String orderId, Order updatedOrder) throws InvalidInputException, OrderNotFoundException {
        try {
            Order existingOrder = orderRepository.getOrder(orderId);
            if (existingOrder == null) {
                throw new OrderNotFoundException("Order not found: " + orderId);
            }

            if (!existingOrder.getOrderId().equals(updatedOrder.getOrderId())) {
                throw new InvalidInputException("Order ID mismatch");
            }

            if (!existingOrder.getCustomerId().equals(updatedOrder.getCustomerId())) {
                throw new InvalidInputException("Customer ID mismatch");
            }

            orderRepository.addOrder(updatedOrder); // Reuse addOrder for update
            return updatedOrder;
        } catch (OrderNotFoundException | InvalidInputException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidInputException("Error updating order: " + e.getMessage());
        }
    }

    public void cancelOrder(String orderId) throws OrderNotFoundException {
        try {
            Order existingOrder = orderRepository.getOrder(orderId);
            if (existingOrder == null) {
                throw new OrderNotFoundException("Order not found: " + orderId);
            }

            Order cancelledOrder = new Order(
                    existingOrder.getOrderId(),
                    existingOrder.getCustomerId(),
                    existingOrder.getItems()
            );

            orderRepository.addOrder(cancelledOrder); // Reuse addOrder for update
        } catch (OrderNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new OrderNotFoundException("Error cancelling order: " + e.getMessage());
        }
    }
}
