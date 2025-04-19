/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.repository;

import com.raqeeb.bookstore.bookstoreapi.model.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raqeeb
 */
public class BookRepository {
    private static final BookRepository instance = new BookRepository();
    
    private final Map<String, Book> bookStorage = new HashMap<>();
    
    private BookRepository() {    }
    
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
}
