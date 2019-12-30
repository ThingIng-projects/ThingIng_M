package com.thinging.project.exceptions;

import com.thinging.project.errors.ErrorCode;

public class ServiceUnavailableException extends RuntimeException {
    private ErrorCode errorCode;

    public ServiceUnavailableException(final ErrorCode errorCode, final String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
