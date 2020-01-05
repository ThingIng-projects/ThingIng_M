package com.thinging.project.mqtt.callback;

import com.thinging.project.client.EndpointManager;
import com.thinging.project.eventManagement.dto.MQTTEventData;
import com.thinging.project.eventManagement.request.MQTTEventRequest;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ThingIngMqttEventCallback implements MqttCallback {

   private MQTTEventRequest eventRequestData;
   private String token;
   private  EndpointManager endpointManager;

   public ThingIngMqttEventCallback(EndpointManager endpointManager, MQTTEventRequest mqttEventData, String token){
       this.eventRequestData = mqttEventData;
       this.token = token;
       this.endpointManager = endpointManager;
   }

    @Override
    public void connectionLost(Throwable throwable) {
        throw new RuntimeException("connectionLost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        MQTTEventData responseData = new MQTTEventData();
        responseData.setTopic(s);
        responseData.setId(mqttMessage.getId());
        responseData.setPayload(new String(mqttMessage.getPayload()));

        endpointManager.mqttSendEvent(token, responseData);
        System.out.println("Message delivered "+responseData);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
