package com.thinging.project.controller;

import com.thinging.project.request.MQTTEventDataRequest;
import com.thinging.project.service.ThingIngMqttService;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping("/test/subscribe")
    public ResponseEntity<?> subscribeToTopic(
            @RequestHeader("Authorization") String token,
            @RequestParam("topic") String topic,
            @RequestParam("qos")int qos ) throws MqttException {

        return respondOK(mqttService.mqttTestSubscribe(topic,qos));
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
