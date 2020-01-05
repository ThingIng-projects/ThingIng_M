package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class COAPServerStartedException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.SERVER_STARTED;;

    public COAPServerStartedException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
