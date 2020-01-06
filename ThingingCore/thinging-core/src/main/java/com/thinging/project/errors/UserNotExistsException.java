package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class UserNotExistsException extends RecordConflictException {

    public UserNotExistsException(String message) {
        super(ErrorCode.USER_NOT_EXISTS, message);
    }
}
