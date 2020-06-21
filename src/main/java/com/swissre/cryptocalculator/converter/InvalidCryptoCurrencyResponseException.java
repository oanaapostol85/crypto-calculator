package com.swissre.cryptocalculator.converter;

public class InvalidCryptoCurrencyResponseException extends RuntimeException {

    public InvalidCryptoCurrencyResponseException(String message) {
        super(message);
    }

    public InvalidCryptoCurrencyResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
