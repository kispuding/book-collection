package com.example.bookcollection.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorNotFoundException() {
    }
}