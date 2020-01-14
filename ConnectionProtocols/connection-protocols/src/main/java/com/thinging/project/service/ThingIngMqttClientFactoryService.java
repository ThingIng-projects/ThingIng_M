package com.thinging.project.service;

import com.thinging.project.errors.MqttClientExistsException;
import com.thinging.project.errors.MqttClientNotExistsException;
import com.thinging.project.mqtt.client.ThingIngMQTTClient;
import com.thinging.project.mqtt.config.ThingIngMqttConfiguration;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ThingIngMqttClientFactoryService {

    private Map<String,ThingIngMQTTClient> thingIngMQTTClients;
    private ThingIngMqttConfiguration mqttConfiguration;

    public ThingIngMqttClientFactoryService(ThingIngMqttConfiguration configuration) {
        this.thingIngMQTTClients = new HashMap<>();
        this.mqttConfiguration = configuration;
    }

    public ThingIngMQTTClient createMqttClient() throws MqttException {
        ThingIngMQTTClient client = new ThingIngMQTTClient("tcp://" + mqttConfiguration.getHost() + ":" + mqttConfiguration.getPort(),
                String.format("ThingIngClient_%d",thingIngMQTTClients.entrySet().size()));
        client.connect();
        thingIngMQTTClients.put(client.getClientId(),client);
        return client;
    }

    public ThingIngMQTTClient createMqttClient(String host, int port, String clientId) throws MqttException {
        if(thingIngMQTTClients.containsKey(clientId)) throw new MqttClientExistsException("client with this id Already exists");

        ThingIngMQTTClient client = new ThingIngMQTTClient("tcp://" +host + ":" + port, clientId);
        client.connect();
        thingIngMQTTClients.put(client.getClientId(),client);
        return client;
    }


    public ThingIngMQTTClient getMqttClient(String clientId){
        if(!thingIngMQTTClients.containsKey(clientId) ) throw new MqttClientNotExistsException("client with this id not exists");

        return thingIngMQTTClients.get(clientId);
    }

    public void removeThingIngClient(String clientId){
        if(thingIngMQTTClients.containsKey(clientId))
            thingIngMQTTClients.remove(clientId);

    }

}
