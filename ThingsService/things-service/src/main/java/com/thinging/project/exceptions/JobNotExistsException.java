package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class JobNotExistsException extends RecordConflictException {

    public JobNotExistsException(String message) {
        super(ErrorCode.JOB_NOT_EXISTS, message);
    }
}
