/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.repository;

import com.raqeeb.bookstore.bookstoreapi.model.Author;
import com.raqeeb.bookstore.bookstoreapi.model.Book;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Raqeeb
 */
public class BookRepository {
    private static final BookRepository instance = new BookRepository();
    
    private final Map<String, Book> bookStorage = new HashMap<>();

    private BookRepository() {
        initialize();
    }

    private void initialize() {
        AuthorRepository authorRepo = AuthorRepository.getInstance();
        Author author1 = authorRepo.getAuthor("AUTH001");
        Author author2 = authorRepo.getAuthor("AUTH002");
        Author author3 = authorRepo.getAuthor("AUTH003");

        Book book1 = new Book("1984", author1, "ISBN001", Year.of(1949), 15.99, 50);
        Book book2 = new Book("Animal Farm", author1, "ISBN002", Year.of(1945), 12.99, 45);
        Book book3 = new Book("The Hobbit", author2, "ISBN003", Year.of(1937), 19.99, 60);
        Book book4 = new Book("Murder on the Orient Express", author3, "ISBN004", Year.of(1934), 14.99, 40);

        bookStorage.put(book1.getISBN(), book1);
        bookStorage.put(book2.getISBN(), book2);
        bookStorage.put(book3.getISBN(), book3);
        bookStorage.put(book4.getISBN(), book4);
    }


    public static BookRepository getInstance(){
        return instance;
    }
    
    
    public void addBook(Book book){
        bookStorage.put(book.getISBN(), book);
    }
    
    public Book getBook(String ISBN){
        return bookStorage.get(ISBN);
    }
    
    public List<Book> getAllBooks(){
        return new ArrayList<>(bookStorage.values());
    }
    
    public boolean deleteBook(String ISBN){
        return bookStorage.remove(ISBN) != null;
    }
    
    public boolean updateBook(String ISBN, Book updatedBook){
        if (bookStorage.containsKey(ISBN)){
            bookStorage.put(ISBN, updatedBook);
            return true;
        }
        return false;
    }
    
    public List<Book> getBooksByAuthor(String authorID) {
        return bookStorage.values()
                .stream()
                .filter(book -> book.getAuthor().getAuthorID().equals(authorID))
                .collect(Collectors.toList());
    }
}
