package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class GroupNotExistsException extends RecordConflictException {

    public GroupNotExistsException(String message) {
        super(ErrorCode.GROUP_NOT_EXISTS, message);
    }
}
