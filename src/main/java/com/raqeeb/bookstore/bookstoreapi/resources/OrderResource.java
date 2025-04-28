/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.service.OrderService;
import com.raqeeb.bookstore.bookstoreapi.model.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Raqeeb
 */
@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderService orderService = OrderService.getInstance();

    @POST
    public Response createOrder(@PathParam("customerId") String customerId, Order order) {
        order.setCustomerId(customerId);
        Order created = orderService.createOrder(order.getOrderId(), customerId);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @GET
    public Response getOrders(@PathParam("customerId") String customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return Response.ok()
                .entity(orders)
                .build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(
            @PathParam("customerId") String customerId,
            @PathParam("orderId") String orderId) {
        Order order = orderService.getOrderById(customerId, orderId);
        return Response.ok()
                .entity(order)
                .build();
    }
}