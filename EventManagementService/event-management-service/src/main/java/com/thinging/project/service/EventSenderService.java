package com.thinging.project.service;

import com.thinging.project.request.ThingIngEventDataRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/*
 * @ToDo Make unirest clients for servers
 * */

@Service
public class EventSenderService {
//    private COAPServiceClient coapServiceClient;
//    private MQTTServiceClient mqttServiceClient;
//    private ThingsServiceClient thingsServiceClient;
//
//    public EventSenderService(COAPServiceClient coapServiceClient, MQTTServiceClient mqttServiceClient, ThingsServiceClient thingsServiceClient) {
//        this.coapServiceClient = coapServiceClient;
//        this.mqttServiceClient = mqttServiceClient;
//        this.thingsServiceClient = thingsServiceClient;
//    }


    public ResponseEntity<String> send(ThingIngEventDataRequest requestData,String token){

        switch (requestData.getServiceType()){
            case MQTT_SERVICE:
                return null; //mqttServiceClient.register(token, requestData); @ToDo Make unirest clients for servers

            default:
                return null;
        }


    }

}
