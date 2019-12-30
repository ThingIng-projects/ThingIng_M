package com.thinging.project.eventManagement.dto;


public class COAPEventData extends EventData{

    private String clientId;
    private String payload;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
