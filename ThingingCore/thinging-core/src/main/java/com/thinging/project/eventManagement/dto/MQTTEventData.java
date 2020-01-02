package com.thinging.project.eventManagement.dto;


public class MQTTEventData extends EventData {

    private int Id;
    private String topic;
    private String payload;

    public MQTTEventData() {
    }

    public MQTTEventData(int id, String topic, String payload) {
        Id = id;
        this.topic = topic;
        this.payload = payload;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "MQTTEventData{" +
                "Id=" + Id +
                ", topic='" + topic + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
