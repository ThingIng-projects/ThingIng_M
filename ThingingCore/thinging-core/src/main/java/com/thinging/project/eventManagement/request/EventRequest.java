package com.thinging.project.eventManagement.request;


public class EventRequest {

    private Long timeStamp;

    public EventRequest valueOf(String value){
        setTimeStamp(Long.valueOf(value));
        return this;
    }


    public String toValueString(){
        return String.valueOf(timeStamp);
    }


    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
