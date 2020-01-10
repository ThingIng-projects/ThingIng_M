package com.thinging.project.request;

import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.eventManagement.request.COAPEventRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.eventManagement.type.ServiceType;
import com.thinging.project.request.AbstractThingIngRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class COAPEventDataRequest extends AbstractThingIngRequest {

    @NotNull(message = "eventType.is.required") private EventType eventType;
    @NotNull(message = "executionType.is.required") private ExecutionType executionType;
    @NotNull(message = "serviceType.is.required") private ServiceType serviceType;
    @NotEmpty(message = "event.is.required") private COAPEventRequest coapEventRequest;
    @NotEmpty(message = "action.is.required") private ThingIngAction action;

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

    public COAPEventRequest getCoapEventRequest() {
        return coapEventRequest;
    }

    public void setCoapEventRequest(COAPEventRequest coapEventRequest) {
        this.coapEventRequest = coapEventRequest;
    }

    public ThingIngAction getAction() {
        return action;
    }

    public void setAction(ThingIngAction action) {
        this.action = action;
    }


    @Override
    public String toString() {
        return "COAPEventDataRequest{" +
                "eventType=" + eventType +
                ", executionType=" + executionType +
                ", serviceType=" + serviceType +
                ", coapEventRequest=" + coapEventRequest +
                ", action=" + action +
                '}';
    }
}
