/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.service;

import com.raqeeb.bookstore.bookstoreapi.exception.CartNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.exception.OutOfStockException;
import com.raqeeb.bookstore.bookstoreapi.model.Cart;
import com.raqeeb.bookstore.bookstoreapi.repository.CartRepository;

/**
 *
 * @author Raqeeb
 */
public class CartService {

    private static final CartService instance = new CartService();
    private final CartRepository cartRepository;
    private final BookService bookService;

    private CartService() {
        this.cartRepository = CartRepository.getInstance();
        this.bookService = BookService.getInstance();
    }

    public static CartService getInstance() {
        return instance;
    }

    public Cart createCart(Cart cart) throws InvalidInputException {
        if (cart.getCustomerId() == null) {
            throw new InvalidInputException("Customer ID cannot be null");
        }
        cartRepository.addCart(cart);
        return cart;
    }

    public Cart getCartByCustomerId(String customerId) throws CartNotFoundException {
        Cart cart = cartRepository.getCartByCustomerId(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found.");
        }
        return cart;
    }

    public boolean addItemToCart(String customerId, String bookISBN, int quantity) throws CartNotFoundException, OutOfStockException {
        Cart cart = getCartByCustomerId(customerId);
        bookService.checkStock(bookISBN, quantity);
        cart.addItem(bookISBN, quantity);
        cartRepository.updateCart(cart);
        return true;
    }

    public boolean removeItemFromCart(String customerId, String bookISBN) throws CartNotFoundException {
        Cart cart = getCartByCustomerId(customerId);
        cart.removeItem(bookISBN);
        cartRepository.updateCart(cart);
        return true;
    }
}

