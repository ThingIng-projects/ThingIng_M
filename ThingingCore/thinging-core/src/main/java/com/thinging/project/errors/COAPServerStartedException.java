package com.thinging.project.errors;

import com.thinging.project.errors.utils.ErrorCode;

public class COAPServerStartedException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.SERVER_STARTED;;

    public COAPServerStartedException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
