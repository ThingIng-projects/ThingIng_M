package com.thinging.project.service;

import com.thinging.project.dto.ConnectionOptionDto;
import com.thinging.project.entity.ConnectionOption;
import com.thinging.project.repository.ConnectionOptionsRepository;
import com.thinging.project.req.ConnectionOptionReq;
import com.thinging.project.utils.parser.DataParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionOptionsService {

    private ConnectionOptionsRepository connectionOptionsRepository;
    private DataParser dataParser;

    public ConnectionOptionsService(ConnectionOptionsRepository connectionOptionsRepository, DataParser dataParser) {
        this.connectionOptionsRepository = connectionOptionsRepository;
        this.dataParser = dataParser;
    }


    public ConnectionOptionDto addConnectionOption(ConnectionOptionReq connectionOptionsReq, String token){
        ConnectionOption connectionOption = dataParser.connectionOptionsReqToEntity(connectionOptionsReq,null);
        return dataParser.entityToConnectionOptionDto(connectionOptionsRepository.save(connectionOption));
    }

    public ConnectionOptionDto updateConnectionOption(Long id,ConnectionOptionReq connectionOptionReq, String token){

        Optional<ConnectionOption> connectionOptionToFind= connectionOptionsRepository.findById(id);
        if (!connectionOptionToFind.isPresent()) throw new RuntimeException("NOt Exists");


        ConnectionOption connectionOption = dataParser.connectionOptionsReqToEntity(connectionOptionReq,connectionOptionToFind.get());
        return dataParser.entityToConnectionOptionDto(connectionOptionsRepository.save(connectionOption));
    }

    public ConnectionOptionDto getConnectionOption(Long id, String token){

        Optional<ConnectionOption> connectionOptionToFind= connectionOptionsRepository.findById(id);
        if (!connectionOptionToFind.isPresent()) throw new RuntimeException("NOt Exists");

        return dataParser.entityToConnectionOptionDto(connectionOptionsRepository.save(connectionOptionToFind.get()));
    }

    public List<ConnectionOptionDto> getAll(){
        List<ConnectionOptionDto> connectionOptionList =  new ArrayList<>();
        connectionOptionsRepository.findAll().stream().forEach(connectionOption -> connectionOptionList.add(dataParser.entityToConnectionOptionDto(connectionOption)));
        return connectionOptionList;
    }

    public void deleteConnectionOption(Long id){
        Optional<ConnectionOption> connectionOptionToFind= connectionOptionsRepository.findById(id);
        if (!connectionOptionToFind.isPresent()) return;

        connectionOptionsRepository.delete(connectionOptionToFind.get());
    }

}
