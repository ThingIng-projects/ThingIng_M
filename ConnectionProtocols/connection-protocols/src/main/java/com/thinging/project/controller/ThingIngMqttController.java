package com.thinging.project.controller;

import com.thinging.project.service.ThingIngMqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/mqtt")
public class ThingIngMqttController extends AbstractController{

    private ThingIngMqttService mqttService;

    public ThingIngMqttController(Validator validator, ThingIngMqttService mqttService) {
        super(validator);
        this.mqttService = mqttService;
    }

    @GetMapping("/subscribe")
    public ResponseEntity<?> subscribeToTopic(
            @RequestParam("topic") String topic,
            @RequestParam("qos") int qos,
            @RequestHeader("Authorization") String token) throws MqttException {

        return respondOK(mqttService.subscribeToTopic(topic,qos));
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<?>  unSubscribeToTopic(
            @RequestParam("topic-filter") String topicFilter,
            @RequestHeader("Authorization") String token) throws MqttException {

        mqttService.unSubscribeFromTopic(topicFilter);

        return respondEmpty();
    }


    @PostMapping("/publish")
    public String publishToTopic(@RequestParam("topic") String topic,
                                 @RequestParam("qos") int qos,
                                 @RequestBody String message,
                                 @RequestHeader("Authorization") String token) throws MqttException {

            mqttService.publishToTopic(topic, message, qos);


        return "published";
    }



}
