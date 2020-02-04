package com.thinging.project.controller;

import com.thinging.project.dto.TableInfoDto;
import com.thinging.project.req.TableInfoReq;
import com.thinging.project.service.TableInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.List;

@RestController
@RequestMapping("/api/schema-config")
public class TableInfoController extends AbstractController {

    private TableInfoService tableInfoService;

    public TableInfoController(Validator validator, TableInfoService tableInfoService) {
        super(validator);
        this.tableInfoService = tableInfoService;
    }

    @PostMapping
    @ApiOperation("Create Table info")
    private ResponseEntity<TableInfoDto> createTableInfoDto(
            @RequestHeader("Authorization") String token,
            @RequestBody TableInfoReq tableInfoReq){

        return respondOK(tableInfoService.addTableInfo(tableInfoReq,token));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Table info")
    private ResponseEntity<TableInfoDto> updateTableInfoDto(
            @RequestHeader("Authorization") String token,
            @PathVariable("id")Long id,
            @RequestBody TableInfoReq tableInfoReq){

        return respondOK(tableInfoService.updateTableInfo(id,tableInfoReq,token));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Table info")
    private ResponseEntity<TableInfoDto> getTableInfoDto(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long id){

        return respondOK(tableInfoService.getTableInfo(id,token));
    }

    @GetMapping
    @ApiOperation("Get Table infos list")
    private ResponseEntity<List<TableInfoDto>> getTableInfoDtos(
            @RequestHeader("Authorization") String token){

        return respondOK(tableInfoService.getAll());
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Table info")
    private ResponseEntity<?> deleteTableInfoDto(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long id){

        tableInfoService.deleteTableInfo(id);
        return respondEmpty();
    }

}
