package com.thinging.project.response;

import com.thinging.project.eventManagement.dto.EventData;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.eventManagement.type.ServiceType;


public class ThingIngEventData {

    private Long id;

    private EventType eventType;
    private ExecutionType executionType;
    private ServiceType serviceType;


    private EventData eventData;
    private ThingIngAction action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    public ThingIngAction getAction() {
        return action;
    }

    public void setAction(ThingIngAction action) {
        this.action = action;
    }
}
