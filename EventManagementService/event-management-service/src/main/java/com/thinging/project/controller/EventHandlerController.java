package com.thinging.project.controller;

import com.thinging.project.eventManagement.dto.MQTTEventData;
import com.thinging.project.response.ThingIngEventData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventHandlerController{

    @PostMapping("/mqtt/handler")
    public ResponseEntity<String> messageDelivered(
            @RequestHeader("Authorization" ) String token,
            @RequestBody MQTTEventData eventData){


        System.out.println("Message delivered!!!! " + eventData.getPayload());
        System.out.println("Topic information!!! "+eventData.getTopic());

        return ResponseEntity.ok("Successfuly created");
    }

}
