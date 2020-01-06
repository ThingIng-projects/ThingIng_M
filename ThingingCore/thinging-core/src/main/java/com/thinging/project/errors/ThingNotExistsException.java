package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class ThingNotExistsException extends RecordConflictException {

    public ThingNotExistsException(String message) {
        super(ErrorCode.THING_NOT_EXISTS, message);
    }
}
