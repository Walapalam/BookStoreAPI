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
import com.raqeeb.bookstore.bookstoreapi.utils.SuccessMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Raqeeb
 */
@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CartService cartService = CartService.getInstance();

    @GET
    public Response getCart(@PathParam("customerId") String customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        return Response.ok()
                .entity(cart)
                .build();
    }

    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") String customerId, Cart cart) {
        cart.setCustomerId(customerId);
        Cart created = cartService.createCart(cart);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateCartItem(
            @PathParam("customerId") String customerId,
            @PathParam("bookId") String bookId,
            @QueryParam("quantity") int quantity) {
        boolean updated = cartService.addItemToCart(customerId, bookId, quantity);
        return Response.ok()
                .entity(new SuccessMessage("Cart item updated successfully"))
                .build();
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeItemFromCart(
            @PathParam("customerId") String customerId,
            @PathParam("bookId") String bookId) {
        boolean removed = cartService.removeItemFromCart(customerId, bookId);
        return Response.ok()
                .entity(new SuccessMessage("Item removed from cart successfully"))
                .build();
    }
}