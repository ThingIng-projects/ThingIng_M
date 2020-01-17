package com.thinging.project.sysController;

import com.thinging.project.controller.AbstractController;
import com.thinging.project.request.MQTTEventDataRequest;
import com.thinging.project.service.ThingIngMqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/system/mqtt/events")
public class MQTTEventController extends AbstractController {

    private ThingIngMqttService mqttService;

    public MQTTEventController(Validator validator, ThingIngMqttService mqttService) {
        super(validator);
        this.mqttService = mqttService;
    }

    @PostMapping("/set")
    public ResponseEntity<?> create(
            @RequestHeader("Authorization") String token,
            @RequestBody MQTTEventDataRequest eventDataRequest) throws MqttException {

        return respondCreated(mqttService.addClientWithHandler(eventDataRequest,token));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader("Authorization") String token,
            @RequestBody String clientId) throws MqttException {
        mqttService.removeEventHandler(clientId);
        return respondEmpty();
    }

}
