package com.thinging.project.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;

public class ThingIngCOAPAbstractResource extends CoapResource {

    public ThingIngCOAPAbstractResource(String name) {
        super(name);
    }

    @Override
    public void handleGET(CoapExchange exchange) {

        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->{
                    System.out.println(resp.getStringValue());
                });
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->System.out.println(resp.getStringValue()));
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }
}
