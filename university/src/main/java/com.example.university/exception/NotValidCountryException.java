package com.example.university.exception;

public class NotValidCountryException extends RuntimeException {

    public NotValidCountryException(String message) {
        super(message);
    }

}
