package com.thinging.project.controller;

import com.thinging.project.request.COAPEventDataRequest;
import com.thinging.project.service.ThingIngCOAPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Map;

@RestController
@RequestMapping("/api/coap")
@Api(value = "COAP protocol manager")
public class COAPServerController extends AbstractController{

    private ThingIngCOAPService coapServerManager;

    public COAPServerController(Validator validator, ThingIngCOAPService coapServerManager) {
        super(validator);
        this.coapServerManager = coapServerManager;
    }

    @GetMapping("/server/start")
    @ApiOperation("Start coap server")
    public ResponseEntity<String> startCOAPServer(@RequestHeader("Authorization") String token){

        coapServerManager.StartCOAPServer();

        return respondOK("Server started");
    }

    @GetMapping("/server/stop")
    @ApiOperation("Stop coap server")
    public ResponseEntity<String> stopCOAPServer(@RequestHeader("Authorization") String token){

        coapServerManager.stopCOAPServer();

        return respondOK("Server stopped");
    }

    @GetMapping("/resource/create")
    @ApiOperation("Create resource")
    public ResponseEntity<String> addNewResource(
            @RequestHeader("Authorization") String token,
            @RequestParam("resource") String resource){

        coapServerManager.addChildResource(resource);
        return respondCreated(resource);
    }

    @GetMapping("/resource/remove")
    @ApiOperation("Create resource")
    public ResponseEntity<String> removeResource(@RequestHeader("Authorization") String token,
                                                 @RequestParam("resource") String resource){

        coapServerManager.removeChildResource(resource);
        return respondOK("success");
    }

    @GetMapping("/events/create")
    @ApiOperation("Register Event")
    public ResponseEntity<COAPEventDataRequest> createEvent(
            @RequestHeader("Authorization") String token,
            @RequestBody COAPEventDataRequest coapEventDataRequest){

        coapServerManager.addEventHandler(coapEventDataRequest,token);
        return respondCreated(coapEventDataRequest);
    }

    @GetMapping("/events/delete")
    @ApiOperation("Register Event")
    public ResponseEntity<String> deleteEvent(
            @RequestHeader("Authorization") String token,
            @RequestBody String resource){

        coapServerManager.removeEventHandler(resource,token);
        return respondCreated("removed event from "+resource+" resource");
    }

}
