package com.thinging.project.controller;


import com.thinging.project.COAP.request.COAPEventDataRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "COAP events api")
@RequestMapping("/api/coap/events")
public class ThingIngCOAPEventController {

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestHeader String token,
            @RequestBody COAPEventDataRequest coapEventDataRequest){



        return ResponseEntity.ok("success");
    }

}
