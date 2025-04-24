/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.repository;

import com.raqeeb.bookstore.bookstoreapi.model.Author;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Raqeeb
 */
public class AuthorRepository {
    private static final AuthorRepository instance = new AuthorRepository();

    private final Map<String, Author> authorStorage = new HashMap<>();

    private AuthorRepository() {
        initialize();
    }

    public static AuthorRepository getInstance() {
        return instance;
    }
    
    private void initialize() {
        Author author1 = new Author(UUID.randomUUID().toString(), "Alice",
                "Bio");
        Author author2 = new Author(UUID.randomUUID().toString(), "Alice",
                "Bio");
        Author author3 = new Author(UUID.randomUUID().toString(), "Alice",
                "Bio");
        
        this.getInstance().authorStorage.put(author1.getAuthorID().toString(), author1);
        this.getInstance().authorStorage.put(author2.getAuthorID().toString(), author2);
        this.getInstance().authorStorage.put(author3.getAuthorID().toString(), author3);   
    }

    public void addAuthor(Author author) {
        authorStorage.put(author.getAuthorID(), author);
    }

    public Author getAuthor(String authorID) {
        return authorStorage.get(authorID);
    }

    public List<Author> getAllAuthors() {
        return new ArrayList<>(authorStorage.values());
    }

    public boolean deleteAuthor(String authorID) {
        return authorStorage.remove(authorID) != null;
    }

    public boolean updateAuthor(String authorID, Author updatedAuthor) {
        if (authorStorage.containsKey(authorID)) {
            authorStorage.put(authorID, updatedAuthor);
            return true;
        }
        return false;
    }

    public List<Book> getBooksByAuthor(String authorID) {
        return BookRepository.getInstance().getBooksByAuthor(authorID);
    }
}
