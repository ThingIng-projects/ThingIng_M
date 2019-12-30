package com.thinging.project.mqtt.callback;

import com.thinging.project.eventManagement.Request.MQTTEventRequest;
import com.thinging.project.eventManagement.dto.MQTTEventData;
import com.thinging.project.response.ThingIngEventData;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/*  @ToDo Make unirest clients for event management service  */

public class ThingIngMqttEventCallback implements MqttCallback {

   private MQTTEventRequest eventRequestData;
   private String token;

   public ThingIngMqttEventCallback(MQTTEventRequest mqttEventData, String token){
       this.eventRequestData = mqttEventData;
       this.token = token;
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

        System.out.println("Message delivered "+responseData);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
