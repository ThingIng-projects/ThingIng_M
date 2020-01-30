package com.thinging.project.controller;

import com.thinging.project.service.dataServices.DataControlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/data")
public class DataController {

    private DataControlService dataControlService;

    public DataController(DataControlService dataControlService) {
        this.dataControlService = dataControlService;
    }

//    @GetMapping("/create/table")
//    public String createTable(@RequestHeader("Authorization") String token){
//
//
//        return "success";
//    }

}
