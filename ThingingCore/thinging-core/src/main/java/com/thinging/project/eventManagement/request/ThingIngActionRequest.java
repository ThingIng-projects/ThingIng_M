package com.thinging.project.eventManagement.request;

import com.thinging.project.eventManagement.type.Function;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class ThingIngActionRequest {

    private String requestUrl;
    private RequestMethod requestMethod;
    private Map<String, Object> requestParams;
    private Map<String, String> requestHeaders;


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

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}
