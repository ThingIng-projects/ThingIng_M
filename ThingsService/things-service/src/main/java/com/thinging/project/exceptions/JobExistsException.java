package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class JobExistsException extends RecordConflictException {

    public JobExistsException(String message) {
        super(ErrorCode.JOB_EXISTS, message);
    }
}
