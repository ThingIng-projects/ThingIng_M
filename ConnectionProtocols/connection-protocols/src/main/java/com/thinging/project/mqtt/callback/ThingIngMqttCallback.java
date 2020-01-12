package com.thinging.project.mqtt.callback;

import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.action.info.mqtt.MQTTRequestInfo;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.request.MQTTEventDataRequest;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.RequestMethod;

public class ThingIngMqttCallback implements MqttCallback {

   private MQTTEventDataRequest eventDataRequest;
   private ThingIngActionExecutor actionExecutor;

    public ThingIngMqttCallback(MQTTEventDataRequest eventDataRequest,ThingIngActionExecutor actionExecutor) {
        this.eventDataRequest = eventDataRequest;
        this.actionExecutor = actionExecutor;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        throw new RuntimeException("connectionLost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        System.out.println("Message delivered ");
        MQTTRequestInfo requestInfo = new MQTTRequestInfo();
        requestInfo.setTopic(s);
        requestInfo.setId(mqttMessage.getId());
        requestInfo.setPayload(new String(mqttMessage.getPayload()));
        requestInfo.setTimeStamp(System.currentTimeMillis());

        ThingIngAction thingIngAction = new ThingIngAction();

        thingIngAction.setRequestUrl(eventDataRequest.getAction().getRequestUrl());
        thingIngAction.setRequestMethod(RequestMethod.POST);
        thingIngAction.setRequestParams(eventDataRequest.getAction().getRequestParams());
        thingIngAction.setRequestHeaders(eventDataRequest.getAction().getRequestHeaders());

        thingIngAction.setRequestBody(requestInfo);

        actionExecutor.perform(thingIngAction);

        System.out.println("Message delivered "+requestInfo);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
