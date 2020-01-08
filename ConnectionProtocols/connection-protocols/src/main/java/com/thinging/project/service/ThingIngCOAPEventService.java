package com.thinging.project.service;

import com.thinging.project.client.EndpointManager;
import com.thinging.project.errors.EventTypeException;
import com.thinging.project.errors.ServiceTypeException;
import com.thinging.project.eventManagement.request.COAPEventRequest;
import com.thinging.project.eventManagement.request.MQTTEventRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ServiceType;
import com.thinging.project.mqtt.callback.ThingIngMqttEventCallback;
import com.thinging.project.mqtt.client.ThingIngMQTTClient;
import com.thinging.project.request.COAPEventDataRequest;
import com.thinging.project.request.MQTTEventDataRequest;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service
public class ThingIngCOAPEventService {

    private EndpointManager endpointManager;
    private ThingIngCOAPService coapServerManager;

    public ThingIngCOAPEventService(EndpointManager endpointManager, ThingIngCOAPService coapServerManager) {
        this.endpointManager = endpointManager;
        this.coapServerManager = coapServerManager;
    }

    public COAPEventRequest createNewEvent(COAPEventDataRequest coapEventDataRequest, String token) throws MqttException {
        return  coapServerManager.addEventHandler(coapEventDataRequest,token);
    }

    public void removeEvent(COAPEventDataRequest coapEventDataRequest, String token) throws MqttException {

        coapServerManager.removeEventHandler(coapEventDataRequest,token);

    }

}
