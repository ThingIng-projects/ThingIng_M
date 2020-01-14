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
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class ThingIngMqttService {

    private ThingIngActionExecutor actionExecutor;
    private ThingIngMqttClientFactoryService mqttClientFactory;

    public ThingIngMqttService(ThingIngActionExecutor actionExecutor, ThingIngMqttClientFactoryService mqttClientFactory) {
        this.actionExecutor = actionExecutor;
        this.mqttClientFactory = mqttClientFactory;
    }

    public void unSubscribeFromTopic(String clientId,String topic) throws MqttException {

        ThingIngMQTTClient client = mqttClientFactory.getMqttClient(clientId);
        client.unsubscribe(topic);
    }

    public String publishToTopic(String clientId, String topic, String message, int QOS) throws MqttException {

        ThingIngMQTTClient client = mqttClientFactory.getMqttClient(clientId);
        MqttMessage message1 = new MqttMessage();
        message1.setQos(QOS);
        message1.setPayload(message.getBytes());

        client.publish(topic,message1);
        return "Success";
    }

    public String SubscribeToTopic(String clientId,String topic,int Qos) throws MqttException {

        ThingIngMQTTClient client = mqttClientFactory.getMqttClient(clientId);
        client.subscribe(topic,Qos);

        return "Success";
    }

    public String addClientWithHandler(MQTTEventDataRequest eventDataRequest) throws MqttException {

        if (eventDataRequest.getServiceType() != ServiceType.MQTT_SERVICE)
            throw new ServiceTypeException(String.format("expected - %s but received %s",ServiceType.MQTT_SERVICE,eventDataRequest.getServiceType()));
        if(eventDataRequest.getEventType() != EventType.SYSTEM )
            throw new EventTypeException(String.format("expected - %s but received %s",EventType.SYSTEM,eventDataRequest.getEventType()));

        ThingIngMQTTClient mqttClient = mqttClientFactory.getMqttClient(eventDataRequest.getEvent().getClientId());
        mqttClient.setCallback(new ThingIngMqttCallback(eventDataRequest, actionExecutor));

        return String.format("{\"clientId\": \" %s\"}", mqttClient.getClientId());
    }

    public String createClient(String host, int port, String clientId) throws MqttException {
        ThingIngMQTTClient mqttClient = mqttClientFactory.createMqttClient(host, port, clientId);
        return mqttClient.getClientId();
    }

    public void removeEventHandler(String clientId) {
        ThingIngMQTTClient mqttClient = mqttClientFactory.getMqttClient(clientId);
        mqttClient.setCallback(null);
    }

    public void subscribeToTopic(String clientId, String topic, int qos) throws MqttException {
        ThingIngMQTTClient mqttClient = mqttClientFactory.getMqttClient(clientId);

        if(!mqttClient.isConnected()) mqttClient.connect();
        mqttClient.subscribe(topic,qos);
    }
}
