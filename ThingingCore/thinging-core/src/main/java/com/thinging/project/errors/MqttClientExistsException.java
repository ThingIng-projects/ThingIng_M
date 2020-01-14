package com.thinging.project.errors;


import com.thinging.project.errors.utils.ErrorCode;

public class MqttClientExistsException extends RecordConflictException {

    public MqttClientExistsException(String message) {
        super(ErrorCode.MQTT_CLIENT_EXISTS, message);
    }
}
