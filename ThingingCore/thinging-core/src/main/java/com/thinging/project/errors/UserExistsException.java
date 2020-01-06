package com.thinging.project.errors;
import com.thinging.project.errors.utils.ErrorCode;

public class UserExistsException extends RecordConflictException {

    public UserExistsException(String message) {
        super(ErrorCode.USER_EXISTS, message);
    }
}
