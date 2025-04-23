/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.service.OrderService;
import com.raqeeb.bookstore.bookstoreapi.model.Order;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.exception.OrderNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.BookNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Raqeeb
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    
    private final OrderService orderService = OrderService.getInstance();
    
    @POST
    public Response createOrder(Order order) {
        try {
            Order newOrder = orderService.createOrder(order.getOrderId(), order.getCustomerId());
            return Response.status(Response.Status.CREATED)
                         .entity(newOrder)
                         .build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                         .entity(e.getMessage())
                         .build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @GET
    @Path("/{orderId}")
    public Response getOrderById(
            @PathParam("orderId") String orderId,
            @QueryParam("customerId") String customerId) {
        try {
            Order order = orderService.getOrderById(customerId, orderId);
            return Response.ok(order).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/customer/{customerId}")
    public Response getOrdersByCustomer(@PathParam("customerId") String customerId) {
        try {
            List<Order> orders = orderService.getOrdersByCustomer(customerId);
            return Response.ok(orders).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                         .entity(e.getMessage())
                         .build();
        }
    }
    
    @DELETE
    @Path("/{orderId}")
    public Response cancelOrder(@PathParam("orderId") String orderId) {
        try {
            orderService.cancelOrder(orderId);
            return Response.noContent().build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                         .entity(e.getMessage())
                         .build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(e.getMessage())
                         .build();
        } catch (OrderNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(e.getMessage())
                         .build();
        }
    }
}