package com.thinging.project.exceptions;

import com.thinging.project.errors.ErrorCode;

/**
 * Base exception class for record conflicts
 */

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
