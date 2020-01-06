package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class COAPServerNotStartedException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.SERVER_NOT_STARTED;;

    public COAPServerNotStartedException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
