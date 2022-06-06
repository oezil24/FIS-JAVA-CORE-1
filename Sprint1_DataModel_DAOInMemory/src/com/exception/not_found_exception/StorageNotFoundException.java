package com.exception.not_found_exception;

import com.exception.ApplicationException;
import com.service.ApplicationConstant;

public class StorageNotFoundException extends ApplicationException {
    public StorageNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ApplicationConstant.NOT_FOUND_STORAGE;
    }
}
