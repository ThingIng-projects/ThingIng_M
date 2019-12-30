package com.thinging.project.exceptions;


import com.thinging.project.exceptions.utils.ErrorCode;

public class JobStatusInvalidException extends RecordConflictException {

    public JobStatusInvalidException(String message) {
        super(ErrorCode.JOB_STATUS_INVALID, message);
    }
}
