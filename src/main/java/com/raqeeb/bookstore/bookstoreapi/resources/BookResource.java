/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.exception.BookNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import com.raqeeb.bookstore.bookstoreapi.service.BookService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Raqeeb
 */

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private BookService bookService = BookService.getInstance();
    
    @GET
    public Response getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return Response.ok(books).build();
    }
    
    @GET
    @Path("/{isbn}")
    public Response getBookById(@PathParam("isbn") String isbn){
        try{
            Book book = bookService.getBookByISBN(isbn);
            return Response.ok(book).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } 
    }
    
    @POST
    public Response addBook(Book book) {
        try {
            Book addedBook = bookService.createBook(book);
            // 201 Created when a new book is successfully added
            return Response.status(Response.Status.CREATED).entity(addedBook).build();
        } catch (InvalidInputException e) {
            // 400 Bad Request if the input is invalid (e.g., missing fields, wrong data types)
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{isbn}")
    public Response updateBook(@PathParam("isbn") String isbn, Book updatedBook) {
        try {
            boolean response = bookService.updateBook(isbn, updatedBook);
            // 200 OK if the book was successfully updated
            return Response.ok(response).build();
        } catch (BookNotFoundException e) {
            // 404 Not Found if the book is not found
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException e) {
            // 400 Bad Request if the input is invalid (e.g., wrong data format or missing fields)
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") String isbn) {
        try {
            bookService.deleteBook(isbn);
            // 204 No Content if the book was successfully deleted
            return Response.noContent().build();
        } catch (BookNotFoundException e) {
            // 404 Not Found if the book is not found
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
