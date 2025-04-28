/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

import com.raqeeb.bookstore.bookstoreapi.model.Author;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import com.raqeeb.bookstore.bookstoreapi.service.AuthorService;
import com.raqeeb.bookstore.bookstoreapi.utils.SuccessMessage;

import javax.ws.rs.Path;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Raqeeb
 */
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private final AuthorService authorService = AuthorService.getInstance();

    @GET
    public Response getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return Response.ok()
                .entity(authors)
                .build();
    }

    @GET
    @Path("/{authorId}")
    public Response getAuthorById(@PathParam("authorId") String authorId) {
        Author author = authorService.getAuthorById(authorId);
        return Response.ok()
                .entity(author)
                .build();
    }

    @POST
    public Response addAuthor(Author author) {
        Author created = authorService.createAuthor(author);
        return Response.status(Response.Status.CREATED)
                .entity(new SuccessMessage("Author created successfully"))
                .build();
    }

    @PUT
    @Path("/{authorId}")
    public Response updateAuthor(@PathParam("authorId") String authorId, Author updatedAuthor) {
        boolean updated = authorService.updateAuthor(authorId, updatedAuthor);
        return Response.ok()
                .entity(new SuccessMessage("Author updated successfully"))
                .build();
    }

    @DELETE
    @Path("/{authorId}")
    public Response deleteAuthor(@PathParam("authorId") String authorId) {
        authorService.deleteAuthor(authorId);
        return Response.ok()
                .entity(new SuccessMessage("Author deleted successfully"))
                .build();
    }

    @GET
    @Path("/{authorId}/books")
    public Response getBooksByAuthor(@PathParam("authorId") String authorId) {
        List<Book> books = authorService.getBooksByAuthor(authorId);
        return Response.ok()
                .entity(books)
                .build();
    }
}