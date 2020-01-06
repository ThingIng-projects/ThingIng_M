package com.thinging.project.errors;

import com.thinging.project.errors.utils.ErrorCode;

public class MqttServiceException extends RuntimeException {
    private ErrorCode errorCode = ErrorCode.MQTT_ERROR;;

    public MqttServiceException(final String errorMessage){
        super(errorMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
