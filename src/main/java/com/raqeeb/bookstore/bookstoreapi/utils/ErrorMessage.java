/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.utils;

/**
 *
 * @author Raqeeb
 */
public class ErrorMessage {
    private String error;
    private int status;
    private String message;

    public ErrorMessage(String error, int status, String message) {
        this.error = error;
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}