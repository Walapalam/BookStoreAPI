/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.exception;

import com.raqeeb.bookstore.bookstoreapi.utils.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Raqeeb
 */
@Provider
public class CommonExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        ErrorMessage errorMessage;

        if (e instanceof BookNotFoundException ||
                e instanceof AuthorNotFoundException ||
                e instanceof CustomerNotFoundException ||
                e instanceof CartNotFoundException) {
            errorMessage = new ErrorMessage(
                    "Not Found",
                    Response.Status.NOT_FOUND.getStatusCode(),
                    e.getMessage()
            );
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        else if (e instanceof InvalidInputException) {
            errorMessage = new ErrorMessage(
                    "Bad Request",
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    e.getMessage()
            );
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        else if (e instanceof OutOfStockException) {
            errorMessage = new ErrorMessage(
                    "Conflict",
                    Response.Status.CONFLICT.getStatusCode(),
                    e.getMessage()
            );
            return Response.status(Response.Status.CONFLICT)
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Default case for unexpected exceptions
        errorMessage = new ErrorMessage(
                "Internal Server Error",
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                "An unexpected error occurred"
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
