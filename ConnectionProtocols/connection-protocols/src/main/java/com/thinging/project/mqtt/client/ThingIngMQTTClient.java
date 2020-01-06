package com.thinging.project.mqtt.client;


import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ThingIngMQTTClient extends MqttClient {

    private MqttConnectOptions options;

    public ThingIngMQTTClient(String serverURI, String clientId) throws MqttException {
        super(serverURI, clientId);
        options =  new MqttConnectOptions();
    }


    public MqttConnectOptions getOptions() {
        return options;
    }

    public void setOptions(MqttConnectOptions options) {
        this.options = options;
    }
}
