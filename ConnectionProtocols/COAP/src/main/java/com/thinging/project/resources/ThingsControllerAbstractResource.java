package com.thinging.project.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ThingsControllerAbstractResource extends CoapResource {

    private final String name;

    public ThingsControllerAbstractResource(String name) {
        super(name);
        this.name = name;
    }


    @Override
    public void handleGET(CoapExchange exchange) {

        System.out.println(exchange.getQueryParameter("Handle Get for "+name+" Thing"));
        System.out.println(exchange.getQueryParameter("value"));
        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->{
                    System.out.println(resp.getStringValue());
                });
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        System.out.println(exchange.getRequestText());

        System.out.println(exchange.getRequestCode().value);
        exchange.getRequestOptions().asSortedList()
                .stream()
                .forEach(resp->System.out.println(resp.getStringValue()));
        exchange.respond(CoAP.ResponseCode.CONTENT);
    }
}
