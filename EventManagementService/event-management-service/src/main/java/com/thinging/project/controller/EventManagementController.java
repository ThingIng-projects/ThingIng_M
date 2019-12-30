package com.thinging.project.controller;

import com.thinging.project.request.EventManagementServiceEventDataRequest;
import com.thinging.project.service.EventManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/events")
@Api(value="Event Management Service")
public class EventManagementController extends AbstractController{

    private EventManagementService eventManagementService;

    public EventManagementController(Validator validator,
                                     EventManagementService eventManagementService) {

        super(validator);
        this.eventManagementService = eventManagementService;
    }

    @ApiOperation(value = "Register new Event")
    @PostMapping("/register")
    public ResponseEntity<EventManagementServiceEventDataRequest> register(
            @RequestHeader("Authorization") String token,
            @RequestBody EventManagementServiceEventDataRequest event){

       return respondOK(eventManagementService.createEvent(event,token));
    }

    @ApiOperation(value = "Unregister current Event")
    @GetMapping("/unregister/{id}")
    public void unRegister(@PathVariable("id") Long id){


    }

}
