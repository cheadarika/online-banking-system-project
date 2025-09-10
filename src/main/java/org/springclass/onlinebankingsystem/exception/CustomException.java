package org.springclass.onlinebankingsystem.exception;

import lombok.Getter;

public class CustomException extends RuntimeException {
    @Getter
    private final int status;
    private final String message;

    public CustomException(final int statusCode, final String message) {
        super(message);
        this.status = statusCode;
        this.message = message;
    }

    @Override
    public String getMessage() { return message; }
}
