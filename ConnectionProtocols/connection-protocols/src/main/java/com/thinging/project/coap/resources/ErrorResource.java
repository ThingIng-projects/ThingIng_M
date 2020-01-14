package com.thinging.project.coap.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ErrorResource extends CoapResource {

    private String errorDescription;
    private int code;

    public ErrorResource( String errorDescription, int code) {
        super("Error");
        super.setVisible(false);
        this.errorDescription = errorDescription;
        this.code = code;
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.BAD_REQUEST,String.format("{description: %s,code: %d}",errorDescription,code));
    }

    @Override
    public void handlePOST(CoapExchange exchange) {

        exchange.respond(CoAP.ResponseCode.BAD_REQUEST,String.format("{description: %s,code: %d}",errorDescription,code));
    }

    @Override
    public void handlePUT(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.BAD_REQUEST,String.format("{description: %s,code: %d}",errorDescription,code));
    }

    @Override
    public void handleDELETE(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.BAD_REQUEST,String.format("{description: %s,code: %d}",errorDescription,code));
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
