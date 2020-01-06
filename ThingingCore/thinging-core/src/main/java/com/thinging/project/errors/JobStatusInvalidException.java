package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class JobStatusInvalidException extends RecordConflictException {

    public JobStatusInvalidException(String message) {
        super(ErrorCode.JOB_STATUS_INVALID, message);
    }
}
