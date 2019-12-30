package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class GroupExistsException extends RecordConflictException {

    public GroupExistsException(String message) {
        super(ErrorCode.GROUP_EXISTS, message);
    }
}
