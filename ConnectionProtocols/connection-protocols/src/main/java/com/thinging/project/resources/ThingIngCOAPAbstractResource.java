package com.thinging.project.resources;

import com.thinging.project.eventManagement.request.COAPEventRequest;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThingIngCOAPAbstractResource extends CoapResource {

    private List<COAPEventRequest> eventRequestList;

    public ThingIngCOAPAbstractResource(String name) {
        super(name);
        eventRequestList = new ArrayList<>();
    }

    @Override
    public void handleGET(CoapExchange exchange) {

        System.out.println("Received Get");

        if(eventRequestList.size() > 0 ){



            exchange.getRequestOptions().asSortedList()
                    .stream()
                    .forEach(resp->{
                        System.out.print("value: "+resp.getStringValue()+"; ");
                    });
            exchange.respond(CoAP.ResponseCode.CONTENT);

        }


    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        System.out.println("Request text = "+exchange.getRequestText());
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

    public COAPEventRequest addEvent(COAPEventRequest coapEventData) {
        eventRequestList.add(coapEventData);
        return coapEventData;
    }


}
