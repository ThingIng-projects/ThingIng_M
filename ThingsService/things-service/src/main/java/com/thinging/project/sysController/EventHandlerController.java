package com.thinging.project.sysController;

import com.thinging.project.action.info.coap.COAPRequestInfo;
import com.thinging.project.controller.AbstractController;
import com.thinging.project.eventManagement.dto.MQTTEventData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/events")
public class EventHandlerController extends AbstractController {

    protected EventHandlerController(Validator validator) {
        super(validator);
    }

    @PostMapping("/mqtt/handler")
    public ResponseEntity<String> messageDelivered(
            @RequestHeader("Authorization" ) String token,
            @RequestBody MQTTEventData eventData){


        System.out.println("Message delivered!!!! " + eventData.getPayload());
        System.out.println("Topic information!!! "+eventData.getTopic());

        return ResponseEntity.ok("Successfuly created");
    }

    @PostMapping("/coap/handler")
    public ResponseEntity<String> messageDelivered(
            @RequestHeader("Authorization" ) String token,
            @RequestBody COAPRequestInfo eventData){


        System.out.println("Resource url: " + eventData.getSourceAddress());
        System.out.println("Payload info: "+new String(eventData.getPayload()));
        System.out.println("timestamp: "+eventData.getTimeStamp());

        return ResponseEntity.ok("Successfuly created");
    }

}
