package com.thinging.project.controller;

import com.thinging.project.service.ThingIngMqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mqtt")
public class ThingIngMqttController {

    private ThingIngMqttService mqttService;

    public ThingIngMqttController(ThingIngMqttService mqttService) {
        this.mqttService = mqttService;
    }

    @GetMapping("/subscribe")
    public String subscribeToTopic(@RequestParam("topic") String topic,
                                   @RequestParam("qos") int qos) throws MqttException {

        return mqttService.subscribeToTopic(topic,qos);
    }

    @GetMapping("/unsubscribe")
    public String unSubscribeToTopic(@RequestParam("topic-filter") String topicFilter) throws MqttException {

        mqttService.unSubscribeFromTopic(topicFilter);

        return "unsubscribe success";
    }


    @PostMapping("/publish")
    public String publishToTopic(@RequestParam("topic") String topic,
                                 @RequestParam("qos") int qos,
                                 @RequestBody String message){

        try {
            mqttService.publishToTopic(topic, message, qos);
        } catch (MqttException e) {
            e.printStackTrace();
            return "failed";
        }

        return "published";
    }



}
