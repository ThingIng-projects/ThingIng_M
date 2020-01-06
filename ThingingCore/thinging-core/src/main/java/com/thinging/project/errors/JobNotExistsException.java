package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class JobNotExistsException extends RecordConflictException {

    public JobNotExistsException(String message) {
        super(ErrorCode.JOB_NOT_EXISTS, message);
    }
}
