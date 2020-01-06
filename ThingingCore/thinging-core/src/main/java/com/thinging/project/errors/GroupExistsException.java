package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class GroupExistsException extends RecordConflictException {

    public GroupExistsException(String message) {
        super(ErrorCode.GROUP_EXISTS, message);
    }
}
