/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.model;

import java.time.Year;

/**
 *
 * @author Raqeeb
 */
public class Book {
    private String title;
    private Author author;
    private String ISBN;
    private Year publicationYear;
    private double price;
    private int stockQuantity;

    public Book(String title, Author author, String ISBN, Year publicationYear, double price, int stockQuantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    public Book(){}
    
    public Author getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public double getPrice() {
        return price;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Book Title: %s \nAuthor: %s \nISBN: %s", title, author, ISBN);
    }    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return ISBN.equals(book.getISBN());
    }

    @Override
    public int hashCode() {
        return ISBN.hashCode();
    }
    
}
