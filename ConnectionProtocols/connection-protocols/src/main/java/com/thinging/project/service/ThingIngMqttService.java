package com.thinging.project.service;

import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.errors.EventTypeException;
import com.thinging.project.errors.ServiceTypeException;
import com.thinging.project.eventManagement.request.MQTTEventRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ServiceType;
import com.thinging.project.mqtt.callback.ThingIngMqttCallback;
import com.thinging.project.mqtt.callback.ThingIngMqttTestCallback;
import com.thinging.project.mqtt.client.ThingIngMQTTClient;
import com.thinging.project.request.MQTTEventDataRequest;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class ThingIngMqttService {

    private ThingIngMQTTClient testClient;
    private ThingIngActionExecutor actionExecutor;
    private ThingIngMqttClientFactoryService mqttClientFactory;

    public ThingIngMqttService(ThingIngMQTTClient testClient, ThingIngActionExecutor actionExecutor, ThingIngMqttClientFactoryService mqttClientFactory) {
        this.testClient = testClient;
        this.actionExecutor = actionExecutor;
        this.mqttClientFactory = mqttClientFactory;
    }

    public String mqttTestSubscribe(String topic, int qos) throws MqttException {

        if(!testClient.isConnected()) testClient.connect();

        testClient.setCallback(new ThingIngMqttTestCallback());
        testClient.subscribe(topic, qos);

        return "Success";
    }
    public String unSubscribeFromTopic(String topic) throws MqttException {
        testClient.unsubscribe(topic);
        return "Success";
    }

    public String publishToTopic(String topic, String message, int QOS) throws MqttException {

        MqttMessage message1 = new MqttMessage();
        message1.setQos(QOS);
        message1.setPayload(message.getBytes());

        testClient.publish(topic,message1);
        return "Success";
    }

    public String addClientWithHandler(MQTTEventDataRequest eventDataRequest) throws MqttException {

        if (eventDataRequest.getServiceType() != ServiceType.MQTT_SERVICE)
            throw new ServiceTypeException(String.format("expected - %s but received %s",ServiceType.MQTT_SERVICE,eventDataRequest.getServiceType()));
        if(eventDataRequest.getEventType() != EventType.SYSTEM )
            throw new EventTypeException(String.format("expected - %s but received %s",EventType.SYSTEM,eventDataRequest.getEventType()));

        ThingIngMQTTClient mqttClient = mqttClientFactory.createMqttClient();
        if(!mqttClient.isConnected()) mqttClient.connect();

        mqttClient.setCallback(new ThingIngMqttCallback(eventDataRequest, actionExecutor));
        mqttClient.subscribe(eventDataRequest.getEvent().getTopic(), eventDataRequest.getEvent().getQos());

        return String.format("{\"clientId\": \" %s\"}", mqttClient.getClientId());
    }

    public String updateExistingClient(String clientId,MQTTEventDataRequest eventDataRequest) throws MqttException {

        if (eventDataRequest.getServiceType() != ServiceType.MQTT_SERVICE)
            throw new ServiceTypeException(String.format("expected - %s but received %s",ServiceType.MQTT_SERVICE,eventDataRequest.getServiceType()));
        if(eventDataRequest.getEventType() != EventType.SYSTEM )
            throw new EventTypeException(String.format("expected - %s but received %s",EventType.SYSTEM,eventDataRequest.getEventType()));

        ThingIngMQTTClient mqttClient = mqttClientFactory.getMqttClient(clientId);

        if(!mqttClient.isConnected()) mqttClient.connect();

        mqttClient.setCallback(new ThingIngMqttCallback(eventDataRequest, actionExecutor));
        mqttClient.subscribe(eventDataRequest.getEvent().getTopic(), eventDataRequest.getEvent().getQos());

        return "Success";
    }

    public void removeEvent(String clientId) throws MqttException {
        mqttClientFactory.removeThingIngClient(clientId);
    }

}
