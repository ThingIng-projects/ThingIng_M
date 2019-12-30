package com.thinging.project.controller;

import com.thinging.project.service.CommonService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("/api/connect")
@Api("Common management")
public class CommonServiceController extends AbstractController{

    private CommonService commonService;

    public CommonServiceController(CommonService commonService , Validator validator) {
        super(validator);
        this.commonService = commonService;
    }

    @GetMapping("/job/things")
    public ResponseEntity<?> jobToThings(
            @RequestHeader("Authorization") String token,
            @RequestHeader("Job") String jobName,
            @RequestHeader("Things") Set<String> thingNames){
        return respondOK(commonService.connectJobToThings(jobName,thingNames));
    }

    @GetMapping("/job/groups")
    public ResponseEntity<?> jobToGroups(
            @RequestHeader("Authorization") String token,
            @RequestHeader("Job") String jobName,
            @RequestHeader("Groups") Set<String> groupNames){

        return respondOK(commonService.connectJobToGroups(jobName,groupNames));
    }

    @GetMapping("/group/things")
    public ResponseEntity<?> thingsToGroup(
            @RequestHeader("Authorization") String token,
            @RequestHeader("Group") String groupName,
            @RequestHeader("Things") Set<String> thingNames){

        return respondOK(commonService.connectThingsToGroup(groupName,thingNames));
    }

}
