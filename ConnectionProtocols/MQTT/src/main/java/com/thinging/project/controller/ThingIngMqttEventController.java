package com.thinging.project.controller;

import com.thinging.project.request.MQTTEventDataRequest;
import com.thinging.project.service.ThingIngMqttEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mqtt/event")
@Api(value="Event Management Service")
public class ThingIngMqttEventController {

    private ThingIngMqttEventService mqttEventService;

    public ThingIngMqttEventController(ThingIngMqttEventService mqttService) {
        this.mqttEventService = mqttService;
    }

    @PostMapping("/new")
    @ApiOperation(value = "Register new MQTT Event")
    public ResponseEntity<String> register(
            @RequestHeader("Authorization") String token,
            @RequestBody MQTTEventDataRequest newEvent) throws MqttException {

       return mqttEventService.createNewEvent(newEvent, token);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "Unregister MQTT Event")
    public ResponseEntity<String> unregister(
            @RequestHeader("Authorization") String token,
            @RequestBody MQTTEventDataRequest unregister) throws MqttException {

        return  mqttEventService.removeEvent(unregister,token);
    }

}
