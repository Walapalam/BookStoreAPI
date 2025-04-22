/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.resources;

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
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{authorId}")
    public Response getAuthorById(@PathParam("authorId") String authorId) {
        try {
            Author author = authorService.getAuthorById(authorId);
            return Response.ok(author).build();
        } catch (AuthorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addAuthor(Author author) {
        try {
            Author addedAuthor = authorService.createAuthor(author);
            return Response.status(Response.Status.CREATED).entity(addedAuthor).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{authorId}")
    public Response updateAuthor(@PathParam("authorId") String authorId, Author updatedAuthor) {
        try {
            boolean response = authorService.updateAuthor(authorId, updatedAuthor);
            return Response.ok(response).build();
        } catch (AuthorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{authorId}")
    public Response deleteAuthor(@PathParam("authorId") String authorId) {
        try {
            authorService.deleteAuthor(authorId);
            return Response.noContent().build();
        } catch (AuthorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{authorId}/books")
    public Response getBooksByAuthor(@PathParam("authorId") String authorId) {
        try {
            List<Book> books = authorService.getBooksByAuthor(authorId);
            return Response.ok(books).build();
        } catch (AuthorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
