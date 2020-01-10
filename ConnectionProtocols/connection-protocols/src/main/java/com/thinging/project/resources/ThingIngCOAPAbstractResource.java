package com.thinging.project.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinging.project.action.ThingIngActionExecutor;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import com.thinging.project.eventManagement.request.COAPEventRequest;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.request.COAPEventDataRequest;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Option;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingIngCOAPAbstractResource extends CoapResource {

    private COAPEventDataRequest eventData;
    private ThingIngActionExecutor actionExecutor;

    public ThingIngCOAPAbstractResource(String name) {
        super(name);
    }

    public ThingIngCOAPAbstractResource(String name, COAPEventDataRequest eventData, ThingIngActionExecutor actionExecutor) {
        super(name);
        this.eventData = eventData;
        this.actionExecutor = actionExecutor;
    }

    public ThingIngCOAPAbstractResource(String name, boolean visible, COAPEventDataRequest eventData, ThingIngActionExecutor actionExecutor) {
        super(name, visible);
        this.eventData = eventData;
        this.actionExecutor = actionExecutor;
    }

    public ThingIngCOAPAbstractResource(String name, ThingIngActionExecutor actionExecutor) {
        super(name);
        this.actionExecutor = actionExecutor;
    }

    public ThingIngActionExecutor getActionExecutor() {
        return actionExecutor;
    }

    public void setActionExecutor(ThingIngActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    @Override
    public void handleGET(CoapExchange exchange) {

//        exchange.getRequestOptions().asSortedList()
//                .stream()
//                .forEach(resp->{
//                    System.out.print("value: "+resp.getStringValue()+"; ");
//                });
        exchange.respond(CoAP.ResponseCode.CONTENT);

    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        System.out.println("Request text = " + exchange.getRequestText());
        System.out.println("Request Options: ");
        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->{
                    System.out.print("value: "+resp.getStringValue()+"; ");
                });
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handlePUT(CoapExchange exchange) {

        System.out.println("Request Body = "+exchange.getRequestText());
        System.out.println("Request Options: ");
        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->{
                    System.out.print("value: "+resp.getStringValue()+"; ");
                });
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handleDELETE(CoapExchange exchange) {

        System.out.println("Request Options: ");
        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->{
                    System.out.print("value: "+resp.getStringValue()+"; ");
                });
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    public COAPEventRequest addEvent(COAPEventDataRequest coapEventData) {
        this.eventData = coapEventData;
        return eventData.getCoapEventRequest();
    }

    public void removeEvent(){
        this.eventData = null;
    }


    private void ExecuteEvent(CoapExchange exchange) throws JsonProcessingException {

        if(eventData == null) return;

        ThingIngAction action = new ThingIngAction();

        action.setRequestUrl(eventData.getAction().getRequestUrl());
        action.setRequestMethod(eventData.getAction().getRequestMethod());
       //@ToDo make handle for it
        Map<String,byte[]> requestOptions = new HashMap<>();

        List<Option> optionList  =  exchange.getRequestOptions().asSortedList();

        for(int i = 0;i<optionList.size();i++)
            requestOptions.put(("Value_" + i), optionList.get(i).getValue());

        exchange.getRequestPayload();
        exchange.getSourceAddress().getHostAddress();
        exchange.getSourcePort();

        action.setRequestBody(action);

        actionExecutor.perform("",action);

        if(eventData.getExecutionType() == ExecutionType.ONE_TIME_EXECUTION) eventData = null;
    }
}
