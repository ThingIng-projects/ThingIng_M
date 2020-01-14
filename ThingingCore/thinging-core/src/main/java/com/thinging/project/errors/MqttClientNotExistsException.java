package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class MqttClientNotExistsException extends RecordConflictException {

    public MqttClientNotExistsException(String message) {
        super(ErrorCode.MQTT_CLIENT_NOT_EXISTS, message);
    }
}
