package com.thinging.project.exceptions;

import com.thinging.project.exceptions.utils.ErrorCode;

public class ThingNotExistsException extends RecordConflictException {

    public ThingNotExistsException(String message) {
        super(ErrorCode.THING_NOT_EXISTS, message);
    }
}
