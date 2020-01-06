package com.thinging.project.errors;

import com.thinging.project.errors.utils.ErrorCode;

public class COAPResourceNotExistsException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.RESORCE_NOT_EXISTS;;

    public COAPResourceNotExistsException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
