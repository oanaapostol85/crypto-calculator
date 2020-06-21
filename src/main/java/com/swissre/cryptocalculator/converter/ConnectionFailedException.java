package com.swissre.cryptocalculator.converter;

public class ConnectionFailedException extends RuntimeException {

    public ConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
