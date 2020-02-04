package com.thinging.project.utils.parser;

import com.thinging.project.dto.*;

import com.thinging.project.entity.*;
import com.thinging.project.eventManagement.request.*;
import com.thinging.project.persistance.EventDataValue;
import com.thinging.project.req.ConnectionOptionReq;
import com.thinging.project.req.TableInfoReq;
import com.thinging.project.request.EventManagementServiceEventDataRequest;
import com.thinging.project.request.ThingIngEventDataRequest;
import com.thinging.project.response.UserAccountDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataParser {

    public ThingRespDto thingToRespDto(Thing thing){
        return new ThingRespDto(thing.getThingId(), thing.getDescription());
    }

    public GroupRespDto groupToDto(ThingGroup group) {

        List<ThingRespDto> thingRespDtos =  new ArrayList<>();

        group.getThings().stream().forEach(thing -> thingRespDtos.add(this.thingToRespDto(thing)));

        return new GroupRespDto(group.getName(), group.getDescription(),thingRespDtos);
    }

    public JobRespDto jobToRespDto(Job job){

        List<ThingRespDto> thingRespDtos =  new ArrayList<>();
        List<GroupRespDto> groupRespDtos =  new ArrayList<>();

        job.getThings().stream().forEach(thing -> thingRespDtos.add(this.thingToRespDto(thing)));
        job.getThingGroups().stream().forEach(group -> groupRespDtos.add(this.groupToDto(group)));

        return new JobRespDto(job.getName(),job.getDescription(),
                job.getExecutionType(),job.getJsonDocumentPath(),
                job.getJobStatus(),thingRespDtos,groupRespDtos);
    }

    public ThingingEvent thingIngEventRequestToEntity(EventManagementServiceEventDataRequest requestData, ThingingEvent existing){

        ThingingEvent event = existing == null ? new ThingingEvent() :existing;
        event.setEventType(requestData.getEventType());
        event.setExecutionType(requestData.getExecutionType());
        EventDataValue eventDataValue = new EventDataValue();
        eventDataValue.setServiceType(requestData.getServiceType());

        switch (requestData.getServiceType()){
            case MQTT_SERVICE:
                if(requestData.getMqttEvent() == null) throw new RuntimeException("expected mqtt service data info");
                eventDataValue.setEventData(requestData.getMqttEvent());
                break;
            case COAP_SERVICE:
                if(requestData.getCoapEvent() == null) throw new RuntimeException("expected coap service data info");
                eventDataValue.setEventData(requestData.getCoapEvent());
                break;
            case DATA_SERVICE:
                if(requestData.getDataEvent() == null) throw new RuntimeException("expected data service data info");
                eventDataValue.setEventData(requestData.getDataEvent());
                break;
            case THINGS_SERVICE:
                if(requestData.getThingsEvent() == null) throw new RuntimeException("expected things data info");
                eventDataValue.setEventData(requestData.getThingsEvent());
                break;
            case CUSTOM_SERVICE:
                if(requestData.getCustomEvent() == null) throw new RuntimeException("expected custom data info");
                eventDataValue.setEventData(requestData.getCustomEvent());
                break;
            default: throw new RuntimeException("Cannot find any service type");
        }
        event.setEventDataValue(eventDataValue);

        return event;
    }

    public ThingIngEventDataRequest eventManagementRequestToThIngIngEvent(EventManagementServiceEventDataRequest requestData) {

        ThingIngEventDataRequest thingIngEventDataRequest = new ThingIngEventDataRequest();
        thingIngEventDataRequest.setExecutionType(requestData.getExecutionType());
        thingIngEventDataRequest.setServiceType(requestData.getServiceType());
        thingIngEventDataRequest.setEventType(requestData.getEventType());
        thingIngEventDataRequest.setAction(requestData.getAction());

        switch (requestData.getServiceType()){
            case MQTT_SERVICE:
                if(requestData.getMqttEvent() == null) throw new RuntimeException("expected mqtt service data info");
                thingIngEventDataRequest.setEvent(requestData.getMqttEvent());
                break;
            case COAP_SERVICE:
                if(requestData.getCoapEvent() == null) throw new RuntimeException("expected coap service data info");
                thingIngEventDataRequest.setEvent(requestData.getCoapEvent());
                break;
            case DATA_SERVICE:
                if(requestData.getDataEvent() == null) throw new RuntimeException("expected data service data info");
                thingIngEventDataRequest.setEvent(requestData.getDataEvent());
                break;
            case THINGS_SERVICE:
                if(requestData.getThingsEvent() == null) throw new RuntimeException("expected things data info");
                thingIngEventDataRequest.setEvent(requestData.getThingsEvent());
                break;
            case CUSTOM_SERVICE:
                if(requestData.getCustomEvent() == null) throw new RuntimeException("expected custom data info");
                thingIngEventDataRequest.setEvent(requestData.getCustomEvent());
                break;
            default: throw new RuntimeException("Cannot find any service type");
        }
        return thingIngEventDataRequest;
    }

    public EventManagementServiceEventDataRequest entityToRequestData(ThingingEvent thingingEvent) {

        EventManagementServiceEventDataRequest request = new EventManagementServiceEventDataRequest();

        request.setEventType(thingingEvent.getEventType());
        request.setExecutionType(thingingEvent.getExecutionType());
        request.setServiceType(thingingEvent.getEventDataValue().getServiceType());

        ThingIngActionRequest action = new ThingIngActionRequest();
        if(thingingEvent.getAction() != null){
            action.setRequestMethod(thingingEvent.getAction().getRequestMethod());
            action.setRequestUrl(thingingEvent.getAction().getRequestUrl());
            action.setRequestHeaders(thingingEvent.getAction().getRequestHeaders());
            action.setRequestParams(thingingEvent.getAction().getRequestParams());
        }

        request.setAction(action);

        if(thingingEvent.getEventDataValue().getEventData() == null) throw new RuntimeException("expected event info");
        switch (thingingEvent.getEventDataValue().getServiceType()){
            case MQTT_SERVICE:
                request.setMqttEvent((MQTTEventRequest) thingingEvent.getEventDataValue().getEventData());
                break;
            case COAP_SERVICE:
                request.setCoapEvent((COAPEventRequest) thingingEvent.getEventDataValue().getEventData());
                break;
            case DATA_SERVICE:
                request.setDataEvent((DataServiceEventRequest) thingingEvent.getEventDataValue().getEventData());
                break;
            case THINGS_SERVICE:
                request.setThingsEvent((ThingsEventRequest) thingingEvent.getEventDataValue().getEventData());
                break;
            case CUSTOM_SERVICE:
                request.setCustomEvent((CustomEventRequest) thingingEvent.getEventDataValue().getEventData());
                break;
            default: throw new RuntimeException("Cannot find any service type");
        }

        return request;
    }

    public UserAccountDto userAccountToDto(UserAccount userAccount){

        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setEmail(userAccount.getEmail());
        userAccountDto.setPassword(userAccount.getPassword());
        userAccountDto.setFirstName(userAccount.getFirstName());
        userAccountDto.setLastName(userAccount.getLastName());
        userAccountDto.setRole(userAccount.getRole());

        return userAccountDto;
    }
    public UserAccount dtoToUserAccount(UserAccountDto userAccountDto,UserAccount existing){

        UserAccount userAccount = existing==null ? new UserAccount():existing;

        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccount.setFirstName(userAccountDto.getFirstName());
        userAccount.setLastName(userAccountDto.getLastName());
        userAccount.setRole(userAccountDto.getRole());

        return userAccount;
    }

    public TableInfo tableInfoRequestToEntity(TableInfoReq tableInfoRequest, TableInfo existing){
        TableInfo tableInfo = existing==null ? new TableInfo():existing;

        tableInfo.setName(tableInfoRequest.getName());
        tableInfo.setStorageType(tableInfoRequest.getStorageType());
        tableInfo.setStructure(tableInfoRequest.getStructure());
        tableInfo.setTableType(tableInfoRequest.getTableType());
        return tableInfo;
    }

    public TableInfoDto entityToTableInfoDto(TableInfo tableInfo){

        TableInfoDto tableInfoDto = new TableInfoDto();

        tableInfoDto.setId(tableInfo.getId());
        tableInfoDto.setConnectionOptionDto(entityToConnectionOptionDto(tableInfo.getConnectionOption()));
        tableInfoDto.setName(tableInfo.getName());
        tableInfoDto.setStorageType(tableInfo.getStorageType());
        tableInfoDto.setTableType(tableInfo.getTableType());
        tableInfoDto.setStructure(tableInfo.getStructure());

        return tableInfoDto;
    }

    public ConnectionOption connectionOptionsReqToEntity(ConnectionOptionReq connectionOptionReq, ConnectionOption existing) {
        ConnectionOption connectionOption = existing==null ? new ConnectionOption():existing;

        connectionOption.setDbTable(connectionOptionReq.getDbTable());
        connectionOption.setPassword(connectionOptionReq.getPassword());
        connectionOption.setUrl(connectionOptionReq.getUrl());
        connectionOption.setUserName(connectionOptionReq.getUserName());

        return connectionOption;
    }
    public ConnectionOptionDto entityToConnectionOptionDto(ConnectionOption connectionOption){
        ConnectionOptionDto connectionOptionDto = new ConnectionOptionDto();

        connectionOptionDto.setDbTable(connectionOption.getDbTable());
        connectionOptionDto.setUserName(connectionOption.getUserName());
        connectionOptionDto.setPassword(connectionOption.getPassword());
        connectionOptionDto.setUrl(connectionOption.getUrl());
        connectionOptionDto.setId(connectionOption.getId());

        return connectionOptionDto;
    }
}
