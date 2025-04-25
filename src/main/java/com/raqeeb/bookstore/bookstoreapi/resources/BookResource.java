/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.DTO.BookDTO;
import com.raqeeb.bookstore.bookstoreapi.exception.BookNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import com.raqeeb.bookstore.bookstoreapi.service.BookService;
import com.raqeeb.bookstore.bookstoreapi.utils.SuccessMessage;

import java.time.Year;
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
    public Response getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return Response.ok()
                .entity(books)
                .build();
    }

    @GET
    @Path("/{isbn}")
    public Response getBookById(@PathParam("isbn") String isbn) {
        Book book = bookService.getBookByISBN(isbn);
        return Response.ok()
                .entity(book)
                .build();
    }

    @POST
    public Response addBook(BookDTO bookDTO) {
        Book book = new Book(/* ... */);
        bookService.createBook(book);
        return Response.status(Response.Status.CREATED)
                .entity(new SuccessMessage("Book created successfully"))
                .build();
    }

    @PUT
    @Path("/{isbn}")
    public Response updateBook(BookDTO bookDTO) {
        Book book = new Book(/* ... */);
        boolean updated = bookService.updateBook(book.getISBN(), book);
        return Response.ok()
                .entity(new SuccessMessage("Book updated successfully"))
                .build();
    }

    @DELETE
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") String isbn) {
        bookService.deleteBook(isbn);
        return Response.ok()
                .entity(new SuccessMessage("Book deleted successfully"))
                .build();
    }
}
