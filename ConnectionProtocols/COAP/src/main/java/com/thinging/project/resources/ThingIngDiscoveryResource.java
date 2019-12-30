package com.thinging.project.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.LinkFormat;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import java.util.List;

public class ThingIngDiscoveryResource extends CoapResource {

    public static final String ABOUT = "core";
    private final Resource root;

    public ThingIngDiscoveryResource(Resource root) {
        this(ABOUT, root);
        setVisible(false);
    }

    public ThingIngDiscoveryResource(String name, Resource root) {
        super(name);
        this.root = root;
        setVisible(false);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        List<String> query = exchange.getRequestOptions().getUriQuery();
        if (query.size() <= 1) {
            String tree = discoverTree(root, query);
            exchange.respond(CoAP.ResponseCode.CONTENT, tree, MediaTypeRegistry.APPLICATION_LINK_FORMAT);
        }
        else {
            exchange.respond(CoAP.ResponseCode.BAD_OPTION, "only one search query is supported!", MediaTypeRegistry.TEXT_PLAIN);
        }
    }

    public String discoverTree(Resource root, List<String> queries) {
        StringBuilder buffer = new StringBuilder();
        for (Resource child : root.getChildren()) {
            LinkFormat.serializeTree(child, queries, buffer);
        }
        if (buffer.length() > 1) {
            buffer.setLength(buffer.length() - 1);
        }

        return buffer.toString();
    }
}
