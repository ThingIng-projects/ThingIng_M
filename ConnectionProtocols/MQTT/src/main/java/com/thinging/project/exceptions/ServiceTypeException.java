package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class ServiceTypeException extends RuntimeException {

    public ServiceTypeException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.SERVICE_TYPE_EXCEPTION;
    }
}
