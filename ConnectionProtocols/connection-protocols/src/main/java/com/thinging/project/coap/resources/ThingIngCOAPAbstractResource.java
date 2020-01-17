package com.thinging.project.coap.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.action.info.coap.COAPRequestInfo;
import com.thinging.project.action.info.coap.COAPRequestType;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.eventManagement.request.COAPEventRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.request.COAPEventDataRequest;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Option;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingIngCOAPAbstractResource extends CoapResource {

    private COAPEventDataRequest eventData;
    private ThingIngActionExecutor actionExecutor;
    private ThingIngAction action;
    private String token;

    public ThingIngCOAPAbstractResource(String name) {
        super(name);
    }

    public ThingIngActionExecutor getActionExecutor() {
        return actionExecutor;
    }

    public void setActionExecutor(ThingIngActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    public COAPEventRequest addEvent(COAPEventDataRequest coapEventData) {
        this.eventData = coapEventData;
        this.action = new ThingIngAction();

        if(coapEventData.getEventType() == EventType.CUSTOM){
            action.setRequestUrl(eventData.getAction().getRequestUrl());
            action.setRequestMethod(eventData.getAction().getRequestMethod());
            action.setRequestParams(eventData.getAction().getRequestParams());
            action.setRequestHeaders(eventData.getAction().getRequestHeaders());
            action.setRequestMethod(RequestMethod.POST);
        }else{
            action.setRequestHeaders(Map.of(
                    "Authorization",token,
                    "Content-Type","Application/json"
            ));
            action.setRequestMethod(RequestMethod.POST);
            action.setRequestUrl("http://localhost:8089/system/events/coap/handler");
            action.setRequestParams(new HashMap<>());
        }

        return eventData.getEvent();
    }

    public void removeEvent(){
        this.eventData = null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public void handleGET(CoapExchange exchange) {

        executeEvent(exchange,COAPRequestType.GET);
        exchange.respond(CoAP.ResponseCode.CONTENT);

    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        executeEvent(exchange,COAPRequestType.POST);
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handlePUT(CoapExchange exchange) {

        executeEvent(exchange,COAPRequestType.PUT);
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handleDELETE(CoapExchange exchange) {

        executeEvent(exchange,COAPRequestType.DELETE);
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }


    private void executeEvent(CoapExchange exchange, COAPRequestType requestType) {

        if(eventData == null) return;

        COAPRequestInfo coapRequestInfo = new COAPRequestInfo();
        Map<String,byte[]> requestOptions = new HashMap<>();

        List<Option> optionList  =  exchange.getRequestOptions().asSortedList();

        for(int i = 0;i<optionList.size();i++)
            requestOptions.put(("Param" + i), optionList.get(i).getValue());

        coapRequestInfo.setRequestType(requestType);
        coapRequestInfo.setRequestOptions(requestOptions);
        coapRequestInfo.setSourceAddress(exchange.getSourceAddress().getHostAddress());
        coapRequestInfo.setSourcePort(exchange.getSourcePort());
        coapRequestInfo.setPayload(exchange.getRequestPayload());
        coapRequestInfo.setTimeStamp(System.currentTimeMillis());
        action.setRequestBody(coapRequestInfo);
        try {
            actionExecutor.perform(this.action);
        } catch (JsonProcessingException e) {
            System.out.println("Error!");
        }

        if(eventData.getExecutionType() == ExecutionType.ONE_TIME_EXECUTION) eventData = null;
    }

}
