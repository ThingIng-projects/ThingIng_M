package com.thinging.project.sysController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/data-service")
public class DataServiceController {

    @GetMapping("/tables")
    public ResponseEntity<?> getTables(@RequestHeader("Authorization") String token){

        return null;
    }

    @GetMapping("/tables/{name}")
    public ResponseEntity<?> getTable(
            @RequestHeader("Authorization") String token,
            @PathVariable("name") String name){

        return null;
    }

    @PostMapping("/tables")
    public ResponseEntity<?> addTable(
            @RequestHeader("Authorization") String token,
            @RequestBody String query){

        return null;
    }

    @DeleteMapping("/tables/name")
    public ResponseEntity<?> deleteTable(
            @RequestHeader("Authorization") String token,
            @PathVariable("name") String name){

        return null;
    }




}
