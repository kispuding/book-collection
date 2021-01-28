package com.example.bookcollection.exception;

public class AuthorIdMismatchException extends RuntimeException{

    public AuthorIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorIdMismatchException() {
    }
}
