package com.thinging.project.service;

import com.thinging.project.client.EndpointManager;
import com.thinging.project.eventManagement.request.MQTTEventRequest;
import com.thinging.project.request.MQTTEventDataRequest;
import com.thinging.project.mqtt.callback.ThingIngMqttEventCallback;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ServiceType;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ThingIngMqttEventService {

    private IMqttClient client;
    private EndpointManager endpointManager;

    public ThingIngMqttEventService(IMqttClient client, EndpointManager endpointManager) {
        this.client = client;
        this.endpointManager = endpointManager;
    }

    public ResponseEntity<String> createNewEvent(MQTTEventDataRequest mqttServiceEvent, String token) throws MqttException {

        if (mqttServiceEvent.getServiceType() != ServiceType.MQTT_SERVICE) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        if(mqttServiceEvent.getEventType() != EventType.SYSTEM ) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);;


        MQTTEventRequest event = mqttServiceEvent.getEvent();
        client.setCallback(new ThingIngMqttEventCallback(endpointManager,event, token));
        client.subscribe(event.getTopic(), event.getQos());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeEvent(MQTTEventDataRequest mqttServiceEvent, String token) throws MqttException {

        if(mqttServiceEvent.getEventType() != EventType.SYSTEM ) return null;
        if (mqttServiceEvent.getServiceType() != ServiceType.MQTT_SERVICE) return  null;

        MQTTEventRequest event =  mqttServiceEvent.getEvent();

        client.unsubscribe(event.getTopic());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
