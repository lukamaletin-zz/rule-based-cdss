package com.lukamaletin.cdss.controller.exception;

/**
 * Custom exception.
 * Gets mapped to {@link org.springframework.http.HttpStatus#UNAUTHORIZED} when caught in
 * {@link com.lukamaletin.cdss.controller.exception.resolver.ExceptionResolver}.
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
