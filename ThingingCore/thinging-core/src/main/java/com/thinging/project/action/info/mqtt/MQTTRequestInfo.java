package com.thinging.project.action.info.mqtt;

import com.thinging.project.action.info.MessageRequestInfo;

public class MQTTRequestInfo extends MessageRequestInfo {

    private int Id;
    private String topic;
    private String payload;

    public MQTTRequestInfo() {
    }

    public MQTTRequestInfo(int id, String topic, String payload) {
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

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "MQTTRequestInfo{" +
                "Id=" + Id +
                ", topic='" + topic + '\'' +
                ", payload='" + payload + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
