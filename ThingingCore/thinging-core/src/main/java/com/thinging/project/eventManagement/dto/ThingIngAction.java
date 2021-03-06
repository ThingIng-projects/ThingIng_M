package com.thinging.project.eventManagement.dto;

import com.thinging.project.action.info.MessageRequestInfo;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class ThingIngAction {

    private String requestUrl;
    private RequestMethod requestMethod;
    private Map<String, Object> requestParams;
    private Map<String, String> requestHeaders;
    private MessageRequestInfo requestBody;


    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String,Object> requestParams) {
        this.requestParams = requestParams;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public MessageRequestInfo getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(MessageRequestInfo requestBody) {
        this.requestBody = requestBody;
    }
}
