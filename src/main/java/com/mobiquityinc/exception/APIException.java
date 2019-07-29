package com.mobiquityinc.exception;

public class APIException extends Exception {

    public APIException(final String message, final Exception exception) {
        super(message, exception);
    }

    public APIException(final String message) {
        super(message);
    }
}
