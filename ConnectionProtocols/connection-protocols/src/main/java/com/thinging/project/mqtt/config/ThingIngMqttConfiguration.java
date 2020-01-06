package com.thinging.project.mqtt.config;

import com.thinging.project.mqtt.client.ThingIngMQTTClient;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThingIngMqttConfiguration {

    @Value("${ThingIng.mqtt.config.host}") private String host;
    @Value("${ThingIng.mqtt.config.port}") private int port;
    @Value("${ThingIng.mqtt.config.client.id}") private String clientId;

    private ApplicationContext context;

    public ThingIngMqttConfiguration(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ThingIngMQTTClient mqttClient() throws MqttException {
        return new ThingIngMQTTClient("tcp://" + host + ":" + port, clientId);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ApplicationContext getContext() {
        return context;
    }
}
