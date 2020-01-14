package com.thinging.project.controller;

import com.thinging.project.service.ThingIngMqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/mqtt/client")
public class ThingIngMqttController extends AbstractController{

    private ThingIngMqttService mqttService;

    public ThingIngMqttController(Validator validator, ThingIngMqttService mqttService) {
        super(validator);
        this.mqttService = mqttService;
    }

    @GetMapping("/create")
    public ResponseEntity<?> createClient(
            @RequestHeader("Authorization") String token,
            @RequestParam("mqtt_host") String host,
            @RequestParam("port") int port,
            @RequestParam("client_id") String clientId) throws MqttException {

        return respondOK(mqttService.createClient(host,port,clientId));
    }

    @GetMapping("/remove")
    public ResponseEntity<?>  removeClient(
            @RequestParam("client_id") String clientId,
            @RequestHeader("Authorization") String token) throws MqttException {

        mqttService.removeEventHandler(clientId);
        return respondEmpty();
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishToTopic(
            @RequestParam("client_id") String clientId,
            @RequestParam("topic") String topic,
            @RequestParam("qos") int qos,
            @RequestHeader("Authorization") String token,
            @RequestBody String message) throws MqttException {

        mqttService.publishToTopic(clientId,topic, message, qos);
        return respondEmpty();
    }

    @GetMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            @RequestParam("client_id") String clientId,
            @RequestParam("topic") String topic,
            @RequestParam("qos") int qos,
            @RequestHeader("Authorization") String token) throws MqttException {

        mqttService.subscribeToTopic(clientId, topic, qos);
        return respondEmpty();
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<?> unSubscribe(
            @RequestParam("client_id") String clientId,
            @RequestParam("topic") String topic,
            @RequestHeader("Authorization") String token) throws MqttException {

        mqttService.unSubscribeFromTopic(clientId, topic);
        return respondEmpty();
    }

}
