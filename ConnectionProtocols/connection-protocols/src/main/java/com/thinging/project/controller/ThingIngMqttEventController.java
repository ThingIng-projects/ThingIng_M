package com.thinging.project.controller;

import com.thinging.project.request.MQTTEventDataRequest;
import com.thinging.project.service.ThingIngMqttEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/mqtt/event")
@Api(value="Event Management Service")
public class ThingIngMqttEventController extends AbstractController{

    private ThingIngMqttEventService mqttEventService;

    public ThingIngMqttEventController(Validator validator, ThingIngMqttEventService mqttEventService) {
        super(validator);
        this.mqttEventService = mqttEventService;
    }

    @PostMapping("/new")
    @ApiOperation(value = "Register new MQTT Event")
    public ResponseEntity<?> register(
            @RequestHeader("Authorization") String token,
            @RequestBody MQTTEventDataRequest newEvent) throws MqttException {

//       validateRequest(newEvent);
       return respondCreated(mqttEventService.createNewEvent(newEvent, token));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "Unregister MQTT Event")
    public ResponseEntity<?> unregister(
            @RequestHeader("Authorization") String token,
            @RequestBody MQTTEventDataRequest unregister) throws MqttException {

//        validateRequest(unregister);
        return respondEmpty();
    }

}
