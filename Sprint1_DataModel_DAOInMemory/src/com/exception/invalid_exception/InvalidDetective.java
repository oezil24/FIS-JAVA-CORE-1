package com.exception.invalid_exception;

import com.exception.ApplicationException;

public class InvalidDetective extends ApplicationException {
    public InvalidDetective(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
