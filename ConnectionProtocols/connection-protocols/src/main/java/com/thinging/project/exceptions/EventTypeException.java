package com.thinging.project.exceptions;

import com.thinging.project.exceptions.utils.ErrorCode;

public class EventTypeException extends RuntimeException{

    public EventTypeException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.EVENT_TYPE_EXCEPTION;
    }
}
