/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi;

import com.raqeeb.bookstore.bookstoreapi.resources.AuthorResource;
import com.raqeeb.bookstore.bookstoreapi.resources.BookResource;
import com.raqeeb.bookstore.bookstoreapi.resources.CartResource;
import com.raqeeb.bookstore.bookstoreapi.resources.CustomerResource;
import com.raqeeb.bookstore.bookstoreapi.resources.OrderResource;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("rest")
public class BookStoreAPI extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(AuthorResource.class);
        classes.add(BookResource.class);
        classes.add(OrderResource.class);
        classes.add(CartResource.class);
        classes.add(CustomerResource.class);
        return classes;
    }
}
