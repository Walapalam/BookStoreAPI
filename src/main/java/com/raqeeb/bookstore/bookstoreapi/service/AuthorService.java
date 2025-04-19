/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.service;

import com.raqeeb.bookstore.bookstoreapi.exception.AuthorNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.model.Author;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import com.raqeeb.bookstore.bookstoreapi.repository.AuthorRepository;
import java.util.List;

/**
 *
 * @author Raqeeb
 */
public class AuthorService {

    private static final AuthorService instance = new AuthorService();
    private final AuthorRepository authorRepository;

    private AuthorService() {
        this.authorRepository = AuthorRepository.getInstance();
    }

    public static AuthorService getInstance() {
        return instance;
    }

    public Author createAuthor(Author author) throws InvalidInputException {
        if (author.getName() == null || author.getBiography() == null) {
            throw new InvalidInputException("Author name or biography cannot be null");
        }
        authorRepository.addAuthor(author);
        return author;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    public Author getAuthorById(String authorId) throws AuthorNotFoundException {
        Author author = authorRepository.getAuthor(authorId);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found.");
        }
        return author;
    }

    public boolean updateAuthor(String authorId, Author updatedAuthor) throws InvalidInputException {
        if (updatedAuthor.getName() == null || updatedAuthor.getBiography() == null) {
            throw new InvalidInputException("Updated author name or biography cannot be null");
        }
        return authorRepository.updateAuthor(authorId, updatedAuthor);
    }

    public boolean deleteAuthor(String authorId) throws AuthorNotFoundException {
        if (!authorRepository.deleteAuthor(authorId)) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found.");
        }
        return true;
    }

    public List<Book> getBooksByAuthor(String authorId) throws AuthorNotFoundException {
        Author author = getAuthorById(authorId);
        return authorRepository.getBooksByAuthor(author.getAuthorID());
    }
}
