/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.exception.CustomerNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.model.Customer;
import com.raqeeb.bookstore.bookstoreapi.service.CustomerService;
import com.raqeeb.bookstore.bookstoreapi.utils.SuccessMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * RESTful API resource for managing customers.
 * 
 * @author Raqeeb
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private final CustomerService customerService = CustomerService.getInstance();

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok()
                .entity(customers)
                .build();
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomerById(@PathParam("customerId") String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return Response.ok()
                .entity(customer)
                .build();
    }

    @POST
    public Response createCustomer(Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{customerId}")
    public Response updateCustomer(@PathParam("customerId") String customerId, Customer updatedCustomer) {
        boolean updated = customerService.updateCustomer(customerId, updatedCustomer);
        return Response.ok()
                .entity(new SuccessMessage("Customer updated successfully"))
                .build();
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") String customerId) {
        customerService.deleteCustomer(customerId);
        return Response.ok()
                .entity(new SuccessMessage("Customer deleted successfully"))
                .build();
    }
}