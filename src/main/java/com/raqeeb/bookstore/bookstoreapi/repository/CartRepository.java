/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.repository;

import com.raqeeb.bookstore.bookstoreapi.model.Cart;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class CartRepository {

    private static final CartRepository instance = new CartRepository();
    private final Map<String, Cart> cartStorage = new HashMap<>();

    private CartRepository() {}

    public static CartRepository getInstance() {
        return instance;
    }

    // Matches CartService.getCartByCustomerId
    public Cart getCartByCustomerId(String customerId) {
        return cartStorage.get(customerId);
    }

    // Matches CartService.createCart
    public void addCart(Cart cart) {
        cartStorage.put(cart.getCustomerId(), cart);
    }

    // Matches CartService.updateCart
    public void updateCart(Cart cart) {
        cartStorage.put(cart.getCustomerId(), cart);
    }

    public void deleteCart(String customerId) {
        cartStorage.remove(customerId);
    }
}
