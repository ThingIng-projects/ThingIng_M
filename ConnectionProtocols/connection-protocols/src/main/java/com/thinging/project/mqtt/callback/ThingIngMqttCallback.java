package com.thinging.project.mqtt.callback;

import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.action.info.mqtt.MQTTRequestInfo;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.request.MQTTEventDataRequest;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class ThingIngMqttCallback implements MqttCallback {

   private MQTTEventDataRequest eventDataRequest;
   private ThingIngActionExecutor actionExecutor;
   private ThingIngAction thingIngAction;
   private String token;


    public ThingIngMqttCallback(MQTTEventDataRequest eventDataRequest,ThingIngActionExecutor actionExecutor, String token) {
        this.eventDataRequest = eventDataRequest;
        this.actionExecutor = actionExecutor;
        this.token = token;

        thingIngAction = new ThingIngAction();

        if(eventDataRequest.getEventType() == EventType.CUSTOM){
            thingIngAction.setRequestUrl(eventDataRequest.getAction().getRequestUrl());
            thingIngAction.setRequestMethod(eventDataRequest.getAction().getRequestMethod());
            thingIngAction.setRequestParams(eventDataRequest.getAction().getRequestParams());
            thingIngAction.setRequestHeaders(eventDataRequest.getAction().getRequestHeaders());
        }else{
            thingIngAction.setRequestHeaders(Map.of(
                    "Authorization",token,
                    "Content-Type","Application/json"
            ));
            thingIngAction.setRequestMethod(RequestMethod.POST);
            thingIngAction.setRequestUrl("http://localhost:8089/system/events/mqtt/handler");
            thingIngAction.setRequestParams(new HashMap<>());
        }


    }

    @Override
    public void connectionLost(Throwable throwable) {
        throw new RuntimeException("connectionLost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        if(thingIngAction == null) return;

        MQTTRequestInfo requestInfo = new MQTTRequestInfo();
        requestInfo.setClientId(eventDataRequest.getEvent().getClientId());
        requestInfo.setTopic(s);
        requestInfo.setId(mqttMessage.getId());
        requestInfo.setPayload(new String(mqttMessage.getPayload()));
        requestInfo.setTimeStamp(System.currentTimeMillis());

        thingIngAction.setRequestBody(requestInfo);
        actionExecutor.perform(thingIngAction);

        if(eventDataRequest.getExecutionType() == ExecutionType.ONE_TIME_EXECUTION) thingIngAction = null;

        System.out.println("Message delivered: "+requestInfo);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
