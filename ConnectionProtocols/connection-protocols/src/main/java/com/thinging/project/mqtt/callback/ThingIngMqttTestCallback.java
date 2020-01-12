package com.thinging.project.mqtt.callback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ThingIngMqttTestCallback implements MqttCallback {


    @Override
    public void connectionLost(Throwable throwable) {
        throw  new RuntimeException("connectionLost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage){

        System.out.println(new String(mqttMessage.getPayload()));
        System.out.println(s);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
