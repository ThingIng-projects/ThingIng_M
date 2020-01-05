package com.thinging.project.service;

import com.thinging.project.client.EndpointManager;
import com.thinging.project.eventManagement.request.MQTTEventRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ServiceType;
import com.thinging.project.exceptions.EventTypeException;
import com.thinging.project.exceptions.ServiceTypeException;
import com.thinging.project.mqtt.callback.ThingIngMqttEventCallback;
import com.thinging.project.request.MQTTEventDataRequest;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service
public class ThingIngMqttEventService {

    private IMqttClient client;
    private EndpointManager endpointManager;

    public ThingIngMqttEventService(IMqttClient client, EndpointManager endpointManager) {
        this.client = client;
        this.endpointManager = endpointManager;
    }

    public MQTTEventRequest createNewEvent(MQTTEventDataRequest mqttServiceEvent, String token) throws MqttException {

        if (mqttServiceEvent.getServiceType() != ServiceType.MQTT_SERVICE)
            throw new ServiceTypeException(String.format("expected - %s but received %s",ServiceType.MQTT_SERVICE,mqttServiceEvent.getServiceType()));
        if(mqttServiceEvent.getEventType() != EventType.SYSTEM )
            throw new EventTypeException(String.format("expected - %s but received %s",EventType.SYSTEM,mqttServiceEvent.getEventType()));


        MQTTEventRequest event = mqttServiceEvent.getEvent();
        client.setCallback(new ThingIngMqttEventCallback(endpointManager,event, token));
        client.subscribe(event.getTopic(), event.getQos());

        return event;
    }

    public void removeEvent(MQTTEventDataRequest mqttServiceEvent, String token) throws MqttException {

        if(mqttServiceEvent.getEventType() != EventType.SYSTEM )
            throw new ServiceTypeException(String.format("expected - %s but received %s",ServiceType.MQTT_SERVICE,mqttServiceEvent.getServiceType()));
        if (mqttServiceEvent.getServiceType() != ServiceType.MQTT_SERVICE)
            throw new EventTypeException(String.format("expected - %s but received %s",EventType.SYSTEM,mqttServiceEvent.getEventType()));

        MQTTEventRequest event =  mqttServiceEvent.getEvent();
        client.unsubscribe(event.getTopic());
    }

}
