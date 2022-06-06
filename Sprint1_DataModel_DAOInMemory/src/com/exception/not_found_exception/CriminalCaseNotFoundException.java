package com.exception.not_found_exception;

import com.exception.ApplicationException;
import com.service.ApplicationConstant;

public class CriminalCaseNotFoundException extends ApplicationException {
    public CriminalCaseNotFoundException(String message) {
        super(message);
    }
    @Override
    public String getErrorCode() {
        return ApplicationConstant.NOT_FOUND_CRIMINAL_CASE;
    }
}
