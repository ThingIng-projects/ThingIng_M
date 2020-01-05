package com.thinging.project.COAP.mesageDeliverer;

import com.thinging.project.resources.ErrorResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.observe.ObserveManager;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.core.observe.ObservingEndpoint;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.core.server.resources.Resource;

import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

@Service
public class ThingIngCOAPMessageDeliverer implements MessageDeliverer {

    private final Resource root;
    private final ObserveManager observeManager = new ObserveManager();

    public ThingIngCOAPMessageDeliverer(Resource root) {
        this.root = root;
    }

    @Override
    public final void deliverRequest(final Exchange exchange) {
        if (exchange == null) throw new NullPointerException("exchange must not be null");

        Request request = exchange.getRequest();
        String path = request.getOptions().getUriPathString();
        final Resource resource = findResource(path);
        if (resource != null) {
            checkForObserveOption(exchange, resource);
            Executor executor = resource.getExecutor();
            if (executor != null) {
                executor.execute(new Runnable() {
                    public void run() {
                        resource.handleRequest(exchange);
                    }
                });
            } else {
                resource.handleRequest(exchange);
            }
        } else {
            exchange.sendResponse(new Response(CoAP.ResponseCode.NOT_FOUND));
        }
    }

    protected final void checkForObserveOption(final Exchange exchange, final Resource resource) {

        Request request = exchange.getRequest();
        if (CoAP.isObservable(request.getCode()) && request.getOptions().hasObserve() && resource.isObservable()) {

            InetSocketAddress source = request.getSourceContext().getPeerAddress();

            if (request.isObserve()) {
                ObservingEndpoint remote = observeManager.findObservingEndpoint(source);
                ObserveRelation relation = new ObserveRelation(remote, resource, exchange);
                remote.addObserveRelation(relation);
                exchange.setRelation(relation);

            } else if (request.isObserveCancel()) {
                ObserveRelation relation = observeManager.getRelation(source, request.getToken());
                if (relation != null) {
                    relation.cancel();
                }
            }
        }
    }

    protected Resource getRootResource() {
        return root;
    }

    private Resource findResource(String path) {

        Resource current = root.getChild(path);
        if (current == null) return new ErrorResource("Cannot find any resource by path specified.",0);

        System.out.println("Current is " + current.getName());
        return current;
    }

    @Override
    public final void deliverResponse(final Exchange exchange, final Response response) {
        if (response == null) {
            throw new NullPointerException("Response must not be null");
        } else if (exchange == null) {
            throw new NullPointerException("Exchange must not be null");
        } else if (exchange.getRequest() == null) {
            throw new IllegalArgumentException("Exchange does not contain request");
        } else {
            boolean processed = preDeliverResponse(exchange, response);
            if (!processed) {
                exchange.getRequest().setResponse(response);
            }
        }
    }

    protected boolean preDeliverResponse(final Exchange exchange, final Response response) {
        return false;
    }
}

