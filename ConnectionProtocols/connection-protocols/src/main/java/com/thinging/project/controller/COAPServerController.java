package com.thinging.project.controller;

import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.request.COAPEventDataRequest;
import com.thinging.project.service.ThingIngCOAPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Map;

@RestController
@RequestMapping("/api/coap/server")
@Api(value = "COAP protocol manager")
public class COAPServerController extends AbstractController{

    private ThingIngCOAPService coapServerManager;

    public COAPServerController(Validator validator, ThingIngCOAPService coapServerManager, ThingIngActionExecutor actionExecutor) {
        super(validator);
        this.coapServerManager = coapServerManager;
    }

    @GetMapping("/start")
    @ApiOperation("Start coap server")
    public ResponseEntity<String> startCOAPServer(@RequestHeader("Authorization") String token){

        coapServerManager.StartCOAPServer();

        return respondOK("Server started");
    }

    @GetMapping("/stop")
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

}
