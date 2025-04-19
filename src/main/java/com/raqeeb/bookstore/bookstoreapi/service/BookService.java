/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.service;

import com.raqeeb.bookstore.bookstoreapi.exception.BookNotFoundException;
import com.raqeeb.bookstore.bookstoreapi.exception.InvalidInputException;
import com.raqeeb.bookstore.bookstoreapi.exception.OutOfStockException;
import com.raqeeb.bookstore.bookstoreapi.model.Book;
import com.raqeeb.bookstore.bookstoreapi.repository.BookRepository;
import java.util.List;

/**
 *
 * @author Raqeeb
 */
public class BookService {

    private static final BookService instance = new BookService();
    private final BookRepository bookRepository;

    private BookService() {
        this.bookRepository = BookRepository.getInstance();
    }

    public static BookService getInstance() {
        return instance;
    }

    public Book createBook(Book book) throws InvalidInputException {
        if (book.getISBN() == null || book.getTitle() == null) {
            throw new InvalidInputException("Book title or ISBN cannot be null");
        }
        bookRepository.addBook(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookByISBN(String ISBN) throws BookNotFoundException {
        Book book = bookRepository.getBook(ISBN);
        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + ISBN + " not found.");
        }
        return book;
    }

    public boolean updateBook(String ISBN, Book updatedBook) throws InvalidInputException {
        if (updatedBook.getISBN() == null || updatedBook.getTitle() == null) {
            throw new InvalidInputException("Updated book title or ISBN cannot be null");
        }
        return bookRepository.updateBook(ISBN, updatedBook);
    }

    public boolean deleteBook(String ISBN) throws BookNotFoundException {
        if (!bookRepository.deleteBook(ISBN)) {
            throw new BookNotFoundException("Book with ISBN " + ISBN + " not found.");
        }
        return true;
    }

    public void checkStock(String ISBN, int quantity) throws OutOfStockException {
        Book book = getBookByISBN(ISBN);
        if (book.getStockQuantity() < quantity) {
            throw new OutOfStockException("Not enough stock available for book: " + book.getTitle());
        }
    }
}
