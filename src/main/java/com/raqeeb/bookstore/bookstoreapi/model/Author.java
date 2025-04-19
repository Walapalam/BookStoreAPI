/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.model;

import java.util.Objects;

/**
 *
 * @author Raqeeb
 */
public class Author {
    private String authorID;
    private String name;
    private String biography;

    public Author(String authorID, String name, String biography) {
        this.authorID = authorID;
        this.name = name;
        this.biography = biography;
    }

    public String getBiography() {
        return biography;
    }

    public String getName() {
        return name;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return String.format("Author: %s (ID: %s)", name, authorID);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return Objects.equals(authorID, author.getAuthorID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorID);
    }
}
