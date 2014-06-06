package com.vhealth.api.utils.exceptions;

public class InvalidPayloadException extends RuntimeException {


    public InvalidPayloadException(String message) {
        super(message);
    }

    public InvalidPayloadException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidPayloadException() {
    }
}
