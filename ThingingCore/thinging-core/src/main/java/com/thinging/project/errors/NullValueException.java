package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class NullValueException extends RecordConflictException {

    public NullValueException(String message) {
        super(ErrorCode.NULL_VALUE, message);
    }
}
