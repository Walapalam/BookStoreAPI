/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raqeeb.bookstore.bookstoreapi.utils;

/**
 *
 * @author Raqeeb
 */
public class SuccessMessage {
    private String message;
    private boolean success;

    public SuccessMessage(String message) {
        this.message = message;
        this.success = true;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}