package com.thinging.project.controller;


import com.thinging.project.request.COAPEventDataRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@Api(value = "COAP events api")
@RequestMapping("/api/coap/events")
public class ThingIngCOAPEventController extends AbstractController{

    protected ThingIngCOAPEventController(Validator validator) {
        super(validator);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestHeader("Authorization") String token,
            @RequestBody COAPEventDataRequest coapEventDataRequest){



        return respondCreated("coapEventDataRequest");
    }

}
