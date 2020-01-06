package com.thinging.project.errors;

import com.thinging.project.errors.utils.ErrorCode;

public class ServiceTypeException extends RuntimeException{

    public ServiceTypeException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.WRONG_SERVICE_TYPE;
    }
}
