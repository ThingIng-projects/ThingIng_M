package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class JobExistsException extends RecordConflictException {

    public JobExistsException(String message) {
        super(ErrorCode.JOB_EXISTS, message);
    }
}
