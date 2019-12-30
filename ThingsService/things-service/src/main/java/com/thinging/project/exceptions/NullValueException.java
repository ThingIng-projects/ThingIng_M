package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class NullValueException extends RecordConflictException {

    public NullValueException(String message) {
        super(ErrorCode.NULL_VALUE, message);
    }
}
