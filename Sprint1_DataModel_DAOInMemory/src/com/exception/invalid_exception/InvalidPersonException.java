package com.exception.invalid_exception;

import com.exception.ApplicationException;
import com.service.ApplicationConstant;

public class InvalidPersonException extends ApplicationException {
    public InvalidPersonException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ApplicationConstant.INVALID_PERSON;
    }

}
