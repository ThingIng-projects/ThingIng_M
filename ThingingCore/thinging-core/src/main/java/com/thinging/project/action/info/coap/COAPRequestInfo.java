package com.thinging.project.action.info.coap;

import com.thinging.project.action.info.MessageRequestInfo;

import java.util.Map;

public class COAPRequestInfo extends MessageRequestInfo {

    private COAPRequestType requestType;
    private Map<String,byte[]> requestOptions;
    private byte[] payload;
    private String sourceAddress;
    private int sourcePort;


    public COAPRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(COAPRequestType requestType) {
        this.requestType = requestType;
    }

    public Map<String, byte[]> getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(Map<String, byte[]> requestOptions) {
        this.requestOptions = requestOptions;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
