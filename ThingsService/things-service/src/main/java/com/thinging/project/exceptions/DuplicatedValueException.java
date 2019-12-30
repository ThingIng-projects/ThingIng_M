package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class DuplicatedValueException extends RecordConflictException {

    public DuplicatedValueException(String message) {
        super(ErrorCode.DUPLICATED_VALUE, message);
    }
}
