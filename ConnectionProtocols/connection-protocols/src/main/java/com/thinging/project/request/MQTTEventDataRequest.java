package com.thinging.project.request;

import com.thinging.project.eventManagement.request.MQTTEventRequest;
import com.thinging.project.eventManagement.request.ThingIngActionRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.eventManagement.type.ServiceType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class MQTTEventDataRequest extends AbstractThingIngRequest {

    @NotNull(message = "eventType.is.required") private EventType eventType;
    @NotNull(message = "executionType.is.required") private ExecutionType executionType;
    @NotNull(message = "serviceType.is.required") private ServiceType serviceType;
    @NotEmpty(message = "event.is.required") private MQTTEventRequest event;
    @NotEmpty(message = "action.is.required") private ThingIngActionRequest action;

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

    public MQTTEventRequest getEvent() {
        return event;
    }

    public void setEvent(MQTTEventRequest event) {
        this.event = event;
    }

    public ThingIngActionRequest getAction() {
        return action;
    }

    public void setAction(ThingIngActionRequest action) {
        this.action = action;
    }
}
