package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class RecordConflictException extends RuntimeException {
    private ErrorCode errorCode;

    public RecordConflictException(final ErrorCode errorCode, final String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
