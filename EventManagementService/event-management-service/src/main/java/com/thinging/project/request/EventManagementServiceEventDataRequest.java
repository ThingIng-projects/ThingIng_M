package com.thinging.project.request;

import com.thinging.project.eventManagement.Request.*;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.eventManagement.type.ServiceType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EventManagementServiceEventDataRequest extends AbstractThingIngRequest{


    @NotNull(message = "eventType.is.required")
    private EventType eventType;

    @NotNull(message = "executionType.is.required")
    private ExecutionType executionType;

    @NotNull(message = "serviceType.is.required")
    private ServiceType serviceType;

    @NotEmpty(message = "action.is.required")
    private ThingIngAction action;

    private MQTTEventRequest mqttEvent;
    private COAPEventRequest coapEvent;
    private DataServiceEventRequest dataEvent;
    private ThingsEventRequest thingsEvent;
    private CustomEventRequest customEvent;


    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ExecutionType getExecutionType() {
        return executionType;
    }

    public void setExecutionType(ExecutionType executionType) {
        this.executionType = executionType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ThingIngAction getAction() {
        return action;
    }

    public void setAction(ThingIngAction action) {
        this.action = action;
    }

    public MQTTEventRequest getMqttEvent() {
        return mqttEvent;
    }

    public void setMqttEvent(MQTTEventRequest mqttEvent) {
        this.mqttEvent = mqttEvent;
    }

    public COAPEventRequest getCoapEvent() {
        return coapEvent;
    }

    public void setCoapEvent(COAPEventRequest coapEvent) {
        this.coapEvent = coapEvent;
    }

    public DataServiceEventRequest getDataEvent() {
        return dataEvent;
    }

    public void setDataEvent(DataServiceEventRequest dataEvent) {
        this.dataEvent = dataEvent;
    }

    public ThingsEventRequest getThingsEvent() {
        return thingsEvent;
    }

    public void setThingsEvent(ThingsEventRequest thingsEvent) {
        this.thingsEvent = thingsEvent;
    }

    public CustomEventRequest getCustomEvent() {
        return customEvent;
    }

    public void setCustomEvent(CustomEventRequest customEvent) {
        this.customEvent = customEvent;
    }
}
