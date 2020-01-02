package com.thinging.project.controller;

import com.thinging.project.COAP.ThingIngCOAPServerManager;
import com.thinging.project.resources.ThingIngBaseResource;
import com.thinging.project.resources.ThingsControllerAbstractResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coap")
@Api(value = "COAP protocol manager")
public class COAPServerController {

    private ThingIngCOAPServerManager coapServerManager;

    public COAPServerController(ThingIngCOAPServerManager coapServerManager){
        this.coapServerManager = coapServerManager;
    }

    @GetMapping("/server/start")
    @ApiOperation("Start coap server")
    public String startCOAPServer(@RequestHeader("Authorization") String token){
        coapServerManager.addResource(new ThingIngBaseResource());
        coapServerManager.StartCOAPServer();
        return "{coap:Server started}";
    }

    @GetMapping("/server/stop")
    @ApiOperation("Stop coap server")
    public String stopCOAPServer(@RequestHeader("Authorization") String token){

        coapServerManager.stopCOAPServer();

        return "{coap:Server stopped}";
    }

    @GetMapping("/create/resource")
    @ApiOperation("Create resource")
    public String addNewResource(@RequestHeader("Authorization") String token,
                                 @RequestParam("resource") String resource){
        coapServerManager.addResource(new ThingsControllerAbstractResource(resource));
        return "Resource created :" + resource;
    }
}
