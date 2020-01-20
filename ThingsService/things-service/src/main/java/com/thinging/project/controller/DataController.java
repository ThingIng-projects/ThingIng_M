package com.thinging.project.controller;

import com.thinging.project.service.dataServices.DataControlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/data")
public class DataController {

    private DataControlService sparkService;

    public DataController(DataControlService sparkService) {
        this.sparkService = sparkService;
    }

    @GetMapping("/create/table")
    public String createTable(@RequestHeader("Authorization") String token){

        sparkService.createTable("");

        return "success";
    }

}
