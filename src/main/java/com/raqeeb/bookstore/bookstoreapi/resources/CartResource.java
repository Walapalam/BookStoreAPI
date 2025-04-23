/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.exception.CartNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.exception.OutOfStockException;
import com.raqeeb.bookstore.bookstoreapi.model.Cart;
import com.raqeeb.bookstore.bookstoreapi.service.CartService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Raqeeb
 */
@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    
    private final CartService cartService = CartService.getInstance();
    
    @POST
    public Response createCart(Cart cart) {
        try {
            Cart newCart = cartService.createCart(cart);
            return Response.status(Response.Status.CREATED)
                         .entity(newCart)
                         .build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                         .entity(e.getMessage())
                         .build();
        }
    }
    
    @GET
    @Path("/{customerId}")
    public Response getCart(@PathParam("customerId") String customerId) {
        try {
            Cart cart = cartService.getCartByCustomerId(customerId);
            return Response.ok(cart).build();
        } catch (CartNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(e.getMessage())
                         .build();
        }
    }
    
    @POST
    @Path("/{customerId}/items/{isbn}")
    public Response addItemToCart(
            @PathParam("customerId") String customerId,
            @PathParam("isbn") String isbn,
            @QueryParam("quantity") int quantity) {
        try {
            boolean success = cartService.addItemToCart(customerId, isbn, quantity);
            return Response.ok(success).build();
        } catch (CartNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(e.getMessage())
                         .build();
        } catch (OutOfStockException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                         .entity(e.getMessage())
                         .build();
        }
    }
    
    @DELETE
    @Path("/{customerId}/items/{isbn}")
    public Response removeItemFromCart(
            @PathParam("customerId") String customerId,
            @PathParam("isbn") String isbn) {
        try {
            boolean success = cartService.removeItemFromCart(customerId, isbn);
            return Response.ok(success).build();
        } catch (CartNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(e.getMessage())
                         .build();
            }
        }
    }

