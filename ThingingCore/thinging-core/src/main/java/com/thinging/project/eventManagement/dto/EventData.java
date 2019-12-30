package com.thinging.project.eventManagement.dto;


public class EventData {

    private Long timeStamp;

    public static EventData valueOf(Class<? extends EventData> clazz,String value){

        return null;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
