package com.report.javaservice.exceptions;

public class ExternalServiceUnauthorizedException extends RuntimeException{
    public ExternalServiceUnauthorizedException(String message) {
        super(message);
    }
}
