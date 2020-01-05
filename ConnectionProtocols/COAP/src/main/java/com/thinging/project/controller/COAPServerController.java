package com.thinging.project.controller;

import com.thinging.project.COAP.ThingIngCOAPServerManager;
import com.thinging.project.exceptions.COAPServerNotStartedException;
import com.thinging.project.exceptions.utils.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/coap")
@Api(value = "COAP protocol manager")
public class COAPServerController extends AbstractController{

    private ThingIngCOAPServerManager coapServerManager;

    public COAPServerController(Validator validator, ThingIngCOAPServerManager coapServerManager) {
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

    @GetMapping("/create/resource")
    @ApiOperation("Create resource")
    public ResponseEntity<String> addNewResource(@RequestHeader("Authorization") String token,
                                 @RequestParam("resource") String resource){

        coapServerManager.addChildResource(resource);
        return respondCreated(resource);
    }
}
