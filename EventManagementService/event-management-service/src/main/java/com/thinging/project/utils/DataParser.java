package com.thinging.project.utils;

import com.thinging.project.entity.ThingingEvent;
import com.thinging.project.eventManagement.Request.*;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.events.model.EventDataValue;
import com.thinging.project.request.EventManagementServiceEventDataRequest;
import com.thinging.project.request.ThingIngEventDataRequest;
import org.springframework.stereotype.Service;

@Service
public class DataParser {

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
                thingIngEventDataRequest.setEvent(requestData.getMqttEvent());
                break;
            case DATA_SERVICE:
                if(requestData.getDataEvent() == null) throw new RuntimeException("expected data service data info");
                thingIngEventDataRequest.setEvent(requestData.getMqttEvent());
                break;
            case THINGS_SERVICE:
                if(requestData.getThingsEvent() == null) throw new RuntimeException("expected things data info");
                thingIngEventDataRequest.setEvent(requestData.getMqttEvent());
                break;
            case CUSTOM_SERVICE:
                if(requestData.getCustomEvent() == null) throw new RuntimeException("expected custom data info");
                thingIngEventDataRequest.setEvent(requestData.getMqttEvent());
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

        ThingIngAction action = new ThingIngAction();
        if(thingingEvent.getAction() != null)
            action.setType(thingingEvent.getAction().getFunction());

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
}
