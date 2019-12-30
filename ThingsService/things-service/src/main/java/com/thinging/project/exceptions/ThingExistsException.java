package com.thinging.project.exceptions;

import com.thinging.project.exceptions.utils.ErrorCode;

public class ThingExistsException extends RecordConflictException {

    public ThingExistsException(String message) {
        super(ErrorCode.THING_EXISTS, message);
    }
}
