package com.thinging.project.service;

import com.thinging.project.dto.ConnectionOptionDto;
import com.thinging.project.dto.TableInfoDto;
import com.thinging.project.entity.ConnectionOption;
import com.thinging.project.entity.TableInfo;
import com.thinging.project.repository.ConnectionOptionsRepository;
import com.thinging.project.repository.TableInfoRepository;
import com.thinging.project.req.TableInfoReq;
import com.thinging.project.utils.parser.DataParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableInfoService {

    private TableInfoRepository tableInfoRepository;
    private ConnectionOptionsRepository connectionOptionsRepository;
    private DataParser parser;


    public TableInfoService(TableInfoRepository tableInfoRepository, ConnectionOptionsRepository connectionOptionsRepository, DataParser parser) {
        this.tableInfoRepository = tableInfoRepository;
        this.connectionOptionsRepository = connectionOptionsRepository;
        this.parser = parser;
    }

    public TableInfoDto addTableInfo(TableInfoReq tableInfoRequest, String token){

        Optional<ConnectionOption> connectionOptionToFind = connectionOptionsRepository.findById(tableInfoRequest.getConnectionOptionsId());
        if(connectionOptionToFind.isEmpty()) throw new RuntimeException("Connection option not find");
        TableInfo tableInfo = parser.tableInfoRequestToEntity(tableInfoRequest,null);
        tableInfo.setConnectionOption(connectionOptionToFind.get());

        return parser.entityToTableInfoDto(tableInfoRepository.save(tableInfo));
    }

    public TableInfoDto updateTableInfo(Long id,TableInfoReq tableInfoRequest, String token){

        Optional<TableInfo> tableInfoToFind= tableInfoRepository.findById(id);
        Optional<ConnectionOption> connectionOptionToFind = connectionOptionsRepository.findById(tableInfoRequest.getConnectionOptionsId());

        if (!tableInfoToFind.isPresent()) throw new RuntimeException("NOt Exists");
        if(connectionOptionToFind.isEmpty()) throw new RuntimeException("Connection option not find");

        TableInfo tableInfo = parser.tableInfoRequestToEntity(tableInfoRequest,tableInfoToFind.get());
        tableInfo.setConnectionOption(connectionOptionToFind.get());

        TableInfoDto tableInfoDto = parser.entityToTableInfoDto(tableInfoRepository.save(tableInfo));;

        return tableInfoDto;
    }

    public TableInfoDto getTableInfo(Long id, String token){

        Optional<TableInfo> tableInfoToFind= tableInfoRepository.findById(id);
        if (!tableInfoToFind.isPresent()) throw new RuntimeException("NOt Exists");

        return parser.entityToTableInfoDto(tableInfoToFind.get());
    }

    public List<TableInfoDto> getAll(){
        List<TableInfoDto> tableInfoDtoList =  new ArrayList<>();
        tableInfoRepository.findAll().stream().forEach(tableInfo -> tableInfoDtoList.add(parser.entityToTableInfoDto(tableInfo)));
        return tableInfoDtoList;
    }

    public void deleteTableInfo(Long id){
        Optional<TableInfo> tableInfoToFind= tableInfoRepository.findById(id);
        if (!tableInfoToFind.isPresent()) return;

        tableInfoRepository.delete(tableInfoToFind.get());
    }

}
