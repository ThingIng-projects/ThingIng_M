package com.thinging.project.events.model;

import com.thinging.project.eventManagement.Request.EventRequest;
import com.thinging.project.eventManagement.type.ServiceType;

public class EventDataValue {

    private ServiceType serviceType;
    private Object eventData;


    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Object getEventData() {
        return eventData;
    }

    public void setEventData(Object eventData) {
        this.eventData = eventData;
    }
}
