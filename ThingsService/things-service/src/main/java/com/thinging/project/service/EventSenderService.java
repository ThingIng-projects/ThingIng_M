package com.thinging.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinging.project.client.EndpointManager;
import com.thinging.project.errors.utils.ErrorResponse;
import com.thinging.project.request.ThingIngEventDataRequest;
import com.thinging.project.security.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

@Service
public class EventSenderService {

    private EndpointManager endpointManager;
    private String token;

    public EventSenderService(EndpointManager endpointManager, JwtTokenUtil jwtTokenUtil) {
        this.endpointManager = endpointManager;
        this.token = "Bearer "+jwtTokenUtil.generateSystemTokenWithExpiration(8544L*3600L);
    }

    public ErrorResponse send(ThingIngEventDataRequest requestData) throws JsonProcessingException {

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
