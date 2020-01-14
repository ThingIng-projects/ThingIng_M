package com.thinging.project.eventManagement.request;


public class MQTTEventRequest extends EventRequest {

    private String clientId;
    private String filter;

    @Override
    public String toValueString() {
        return String.format( "%s->%s", getClientId(),getFilter() );
    }

    @Override
    public MQTTEventRequest valueOf(String value) {
        clientId = value.substring(0,value.indexOf("->"));
        filter = value.substring(value.lastIndexOf("->") + 2);
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
