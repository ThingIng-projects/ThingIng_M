package com.thinging.project.eventManagement.request;

import com.thinging.project.eventManagement.type.ThingServiceType;

public class ThingsEventRequest extends EventRequest{

    private int userId;
    private ThingServiceType serviceType;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ThingServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ThingServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
