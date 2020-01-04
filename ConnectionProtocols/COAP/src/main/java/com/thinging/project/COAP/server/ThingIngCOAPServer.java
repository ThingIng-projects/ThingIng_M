package com.thinging.project.COAP.server;

import com.thinging.project.resources.ThingIngDiscoveryResource;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.core.server.ServerInterface;
import org.eclipse.californium.core.server.resources.Resource;
import org.eclipse.californium.elements.util.ExecutorsUtil;
import org.eclipse.californium.elements.util.NamedThreadFactory;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;


@Service
public class ThingIngCOAPServer implements ServerInterface {

    private final Resource root;
    private final NetworkConfig config;
    private MessageDeliverer deliverer;
    private final List<Endpoint> endpoints;
    private ScheduledExecutorService executor;
    private ScheduledExecutorService secondaryExecutor;
    private boolean detachExecutor;
    private boolean running;


    public ThingIngCOAPServer(CoapResource rootResource,NetworkConfig defaultConfig,MessageDeliverer messageDeliverer) {

        this.config = defaultConfig;
        this.root = rootResource;
        this.deliverer = messageDeliverer;

//        CoapResource wellKnown = new CoapResource(".well-known/core");
//        wellKnown.setVisible(false);
//        wellKnown.add();
        root.add(new ThingIngDiscoveryResource(root));
        this.endpoints = new ArrayList<>();
    }

    public synchronized void setExecutors(final ScheduledExecutorService mainExecutor,
                                          final ScheduledExecutorService secondaryExecutor, final boolean detach) {
        if (mainExecutor == null || secondaryExecutor == null) {
            throw new NullPointerException("executors must not be null");
        }
        if (this.executor == mainExecutor && this.secondaryExecutor == secondaryExecutor) {
            return;
        }
        if (running) {
            throw new IllegalStateException("executor service can not be set on running server");
        }

        if (!this.detachExecutor) {
            if (this.executor != null) {
                this.executor.shutdownNow();
            }
            if (this.secondaryExecutor != null) {
                this.secondaryExecutor.shutdownNow();
            }
        }
        this.executor = mainExecutor;
        this.secondaryExecutor = secondaryExecutor;
        this.detachExecutor = detach;
        for (Endpoint ep : endpoints) {
            ep.setExecutors(this.executor, this.secondaryExecutor);
        }
    }

    @Override
    public synchronized void start() {

        if (running) return;
        if (executor == null) {
            setExecutors(ExecutorsUtil.newScheduledThreadPool(//
                    this.config.getInt(NetworkConfig.Keys.PROTOCOL_STAGE_THREAD_COUNT),
                    new NamedThreadFactory("CoapServer(main)#")), //$NON-NLS-1$
                    ExecutorsUtil.newDefaultSecondaryScheduler("CoapServer(secondary)#"), false);
        }
        if (endpoints.isEmpty()) {
            int port = config.getInt(NetworkConfig.Keys.COAP_PORT);
            CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
            builder.setPort(port);
            builder.setNetworkConfig(config);
            addEndpoint(builder.build());
        }

        int started = 0;
        for (Endpoint ep : endpoints) {
            try { ep.start(); ++started; } catch (IOException e) { e.printStackTrace();}
        }
        if (started == 0) { throw new IllegalStateException("None of the server endpoints could be started"); } else { running = true; }
    }

    @Override
    public synchronized void stop() {

        if (running) {
            for (Endpoint ep : endpoints) {
                ep.stop();
            }
            running = false;
        }
    }

    @Override
    public synchronized void destroy() {
        try {
            if (!detachExecutor)
                if (running) {
                    ExecutorsUtil.shutdownExecutorGracefully(2000, executor, secondaryExecutor);
                } else {
                    if (executor !=null) {
                        executor.shutdownNow();
                    }
                    if (secondaryExecutor != null) {
                        secondaryExecutor.shutdownNow();
                    }
                }
        } finally {
            for (Endpoint ep : endpoints) {
                ep.destroy();
            }
            running = false;
        }
    }

    public void setMessageDeliverer(final MessageDeliverer deliverer) {
        this.deliverer = deliverer;
        for (Endpoint endpoint : endpoints) {
            endpoint.setMessageDeliverer(deliverer);
        }
    }

    public MessageDeliverer getMessageDeliverer() {
        return deliverer;
    }


    @Override
    public void addEndpoint(final Endpoint endpoint) {
        endpoint.setMessageDeliverer(deliverer);
        if (executor != null && secondaryExecutor != null) {
            endpoint.setExecutors(executor, secondaryExecutor);
        }
        endpoints.add(endpoint);
    }

    @Override
    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    @Override
    public Endpoint getEndpoint(int port) {
        Endpoint endpoint = null;

        for (Endpoint ep : endpoints) {
            if (ep.getAddress().getPort() == port) {
                endpoint = ep;
            }
        }
        return endpoint;
    }

    @Override
    public Endpoint getEndpoint(InetSocketAddress address) {
        Endpoint endpoint = null;

        for (Endpoint ep : endpoints) {
            if (ep.getAddress().equals(address)) {
                endpoint = ep;
                break;
            }
        }

        return endpoint;
    }

    @Override
    public ThingIngCOAPServer add(Resource... resources) {
        for (Resource r:resources)
            root.add(r);
        return this;
    }

    @Override
    public boolean remove(Resource resource) {
        return root.delete(resource);
    }

    public Resource getRoot() {
        return root;
    }

}
