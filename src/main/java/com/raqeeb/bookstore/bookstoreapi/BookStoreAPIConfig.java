/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi;

/**
 *
 * @author Raqeeb
 */
import com.raqeeb.bookstore.bookstoreapi.exception.CommonExceptionMapper;
import com.raqeeb.bookstore.bookstoreapi.resources.AuthorResource;
import com.raqeeb.bookstore.bookstoreapi.resources.BookResource;
import com.raqeeb.bookstore.bookstoreapi.resources.CartResource;
import com.raqeeb.bookstore.bookstoreapi.resources.CustomerResource;
import com.raqeeb.bookstore.bookstoreapi.resources.OrderResource;
import org.glassfish.jersey.server.ResourceConfig;

public class BookStoreAPIConfig extends ResourceConfig{
    public BookStoreAPIConfig(){
        register(AuthorResource.class);
        register(BookResource.class);
        register(CartResource.class);
        register(CustomerResource.class);
        register(OrderResource.class);
        register(CommonExceptionMapper.class);
    }
}
