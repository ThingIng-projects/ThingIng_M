package com.thinging.project.eventManagement.request;


public class MQTTEventRequest extends EventRequest {

    private String topic;
    private int qos;
    private String filter;

    @Override
    public String toValueString() {
        return String.format( "%s->%d->%s", getTopic(), getQos(), getFilter() );
    }

    @Override
    public MQTTEventRequest valueOf(String value) {
        topic = value.substring(0,value.indexOf("->"));
        qos = Integer.valueOf(value.substring(value.indexOf("->")+2,value.lastIndexOf("->")));
        filter = value.substring(value.lastIndexOf("->")+2);
        return this;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
