package com.thinging.project.errors;

import com.thinging.project.errors.utils.ErrorCode;

public class EventTypeException extends RuntimeException{

    public EventTypeException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.WRONG_EVENT_TYPE;
    }
}
