package com.thinging.project.service;

import com.thinging.project.mqtt.callback.ThingIngMqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class ThingIngMqttService {

    private IMqttClient client;

    public ThingIngMqttService(IMqttClient client) {
        this.client = client;
    }

    public String subscribeToTopic(String topic, int QOS) throws MqttException {

        client.setCallback(new ThingIngMqttCallback());
        client.subscribe(topic, QOS);

        return "Success";
    }

    public String unSubscribeFromTopic(String topic) throws MqttException {
        client.unsubscribe(topic);
        return "Success";
    }

    public String publishToTopic(String topic, String message, int QOS) throws MqttException {

        MqttMessage message1 = new MqttMessage();
        message1.setQos(QOS);
        message1.setPayload(message.getBytes());

        client.publish(topic,message1);
        return "Success";
    }

}
