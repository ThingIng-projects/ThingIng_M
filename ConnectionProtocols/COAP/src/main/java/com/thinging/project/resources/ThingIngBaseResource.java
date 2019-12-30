package com.thinging.project.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ThingIngBaseResource extends CoapResource {

    public ThingIngBaseResource() {
        super("things");
    }

    @Override
    public void handleGET(CoapExchange exchange) {

        System.out.println(exchange.getQueryParameter("value"));
        exchange.getRequestOptions().asSortedList()
                .forEach(resp->{
                    System.out.println(resp.getStringValue());
                });
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        System.out.println(exchange.getRequestCode().value);
        exchange.getRequestOptions().asSortedList()
                .forEach(resp->System.out.println(resp.getStringValue()));
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

}
