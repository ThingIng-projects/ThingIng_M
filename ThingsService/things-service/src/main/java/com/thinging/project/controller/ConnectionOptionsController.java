package com.thinging.project.controller;

import com.thinging.project.dto.ConnectionOptionDto;
import com.thinging.project.req.ConnectionOptionReq;
import com.thinging.project.service.ConnectionOptionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.List;

@RestController
@RequestMapping("/api/connection-options")
public class ConnectionOptionsController extends AbstractController {

    private ConnectionOptionsService connectionOptionsService;

    public ConnectionOptionsController(Validator validator, ConnectionOptionsService connectionOptionsService) {
        super(validator);
        this.connectionOptionsService = connectionOptionsService;
    }

    @PostMapping
    @ApiOperation("Create Connection option")
    private ResponseEntity<ConnectionOptionDto> createConnectionOption(
            @RequestHeader("Authorization") String topic,
            @RequestBody ConnectionOptionReq connectionOptionRequest){

       return respondOK(connectionOptionsService.addConnectionOption(connectionOptionRequest,topic));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Connection option")
    private ResponseEntity<ConnectionOptionDto> updateConnectionOption(
            @RequestHeader("Authorization") String topic,
            @PathVariable("id")Long id,
            @RequestBody ConnectionOptionReq connectionOptionRequest){

        return respondOK(connectionOptionsService.updateConnectionOption(id,connectionOptionRequest,topic));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Connection option")
    private ResponseEntity<ConnectionOptionDto> getConnectionOption(
            @RequestHeader("Authorization") String topic,
            @PathVariable("id") Long id){

        return respondOK(connectionOptionsService.getConnectionOption(id,topic));
    }

    @GetMapping
    @ApiOperation("Get Connection options list")
    private ResponseEntity<List<ConnectionOptionDto>> getConnectionOptions(
            @RequestHeader("Authorization") String topic){

        return respondOK(connectionOptionsService.getAll());
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Connection option")
    private ResponseEntity<?> deleteConnectionOption(
            @RequestHeader("Authorization") String topic,
            @PathVariable("id") Long id){

        connectionOptionsService.deleteConnectionOption(id);
        return respondEmpty();
    }

}
