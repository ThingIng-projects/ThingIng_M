package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class COAPResourceNotExistsException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.RESORCE_NOT_EXISTS_EXCEPTION;;

    public COAPResourceNotExistsException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
