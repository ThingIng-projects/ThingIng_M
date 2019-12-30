package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class GroupNotExistsException extends RecordConflictException {

    public GroupNotExistsException(String message) {
        super(ErrorCode.GROUP_NOT_EXISTS, message);
    }
}
