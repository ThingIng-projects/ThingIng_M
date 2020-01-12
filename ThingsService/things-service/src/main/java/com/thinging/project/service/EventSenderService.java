package com.thinging.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinging.project.client.EndpointManager;
import com.thinging.project.errors.utils.ErrorResponse;
import com.thinging.project.request.ThingIngEventDataRequest;
import org.springframework.stereotype.Service;

@Service
public class EventSenderService {

    private EndpointManager endpointManager;

    public EventSenderService(EndpointManager endpointManager) {
        this.endpointManager = endpointManager;
    }

    public ErrorResponse send(ThingIngEventDataRequest requestData, String token) throws JsonProcessingException {

        switch (requestData.getServiceType()){
            case MQTT_SERVICE:
                return endpointManager.mqttRegisterEvent(token,requestData);
            case COAP_SERVICE:
                return endpointManager.coapRegisterEvent(token,requestData);
            default:
                return null;
        }
    }

}
