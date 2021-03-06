package com.thinging.project.controller;

import com.thinging.project.service.ThingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/things")
@Api(value="Things management")
public class ThingsController extends AbstractController{

    private ThingService thingService;

    public ThingsController(ThingService thingService, Validator validator) {
        super(validator);
        this.thingService = thingService;
    }

    @GetMapping
    @ApiOperation("Get things")
    public ResponseEntity<?> getThings(
            @RequestHeader(value = "Authorization", required = false) String token){
        return respondOK(thingService.getThings());
    }

    @GetMapping("/{thing_id}")
    @ApiOperation("Get thing by id")
    public ResponseEntity<?> getThing(
            @RequestHeader(value = "Authorization", required = false)String token,
            @PathVariable("thing_id") String id){

        return respondOK(thingService.getThing(id));
    }

    @PostMapping
    @ApiOperation("Add thing")
    public ResponseEntity<?> createThing(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam("thing-id") String thingId,
            @RequestParam("description") String description){


        return respondCreated(thingService.addThing(thingId,description));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete thing")
    public void deleteThing(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("id") String id){
        thingService.deleteThing(id);
        respondEmpty();
    }

}
