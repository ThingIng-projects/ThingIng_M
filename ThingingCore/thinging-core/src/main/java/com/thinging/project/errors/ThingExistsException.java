package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class ThingExistsException extends RecordConflictException {

    public ThingExistsException(String message) {
        super(ErrorCode.THING_EXISTS, message);
    }
}
