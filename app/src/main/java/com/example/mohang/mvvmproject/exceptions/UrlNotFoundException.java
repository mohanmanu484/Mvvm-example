package com.example.mohang.mvvmproject.exceptions;

public class UrlNotFoundException extends RuntimeException{

    public UrlNotFoundException(String message) {
        super(message);
    }
}