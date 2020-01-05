package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class COAPServerNotStartedException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.SERVER_NOT_STARTED;;

    public COAPServerNotStartedException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
