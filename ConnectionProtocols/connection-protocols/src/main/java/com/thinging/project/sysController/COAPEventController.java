package com.thinging.project.sysController;

import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.controller.AbstractController;
import com.thinging.project.request.COAPEventDataRequest;
import com.thinging.project.service.ThingIngCOAPService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/system/coap/events")
public class COAPEventController extends AbstractController {

    private ThingIngCOAPService coapServerManager;
    private ThingIngActionExecutor actionExecutor;

    public COAPEventController(Validator validator, ThingIngCOAPService coapServerManager, ThingIngActionExecutor actionExecutor) {
        super(validator);
        this.coapServerManager = coapServerManager;
        this.actionExecutor = actionExecutor;
    }

    @PostMapping("/create")
    @ApiOperation("Register Event")
    public ResponseEntity<COAPEventDataRequest> createEvent(
            @RequestHeader("Authorization") String token,
            @RequestBody COAPEventDataRequest coapEventDataRequest){

        System.out.println(coapEventDataRequest);
        coapServerManager.addEventHandler(coapEventDataRequest, token, actionExecutor);
        return respondCreated(coapEventDataRequest);
    }

    @GetMapping("/delete")
    @ApiOperation("Register Event")
    public ResponseEntity<String> deleteEvent(
            @RequestHeader("Authorization") String token,
            @RequestBody String resource){

        coapServerManager.removeEventHandler(resource,token);
        return respondCreated("removed event from "+resource+" resource");
    }


}
