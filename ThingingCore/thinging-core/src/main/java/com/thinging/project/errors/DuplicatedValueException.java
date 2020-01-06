package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class DuplicatedValueException extends RecordConflictException {

    public DuplicatedValueException(String message) {
        super(ErrorCode.DUPLICATED_VALUE, message);
    }
}
