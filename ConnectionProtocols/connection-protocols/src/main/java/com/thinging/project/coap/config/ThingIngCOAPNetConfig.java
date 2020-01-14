package com.thinging.project.coap.config;

import com.thinging.project.coap.server.ThingIngCOAPServer;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.network.config.NetworkConfigDefaultHandler;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public  class ThingIngCOAPNetConfig {

    private static final File CONFIG_FILE = new File("ThingIngCoap.properties");
    private static final String CONFIG_HEADER = "Thinging CoAP Properties";

    private  NetworkConfigDefaultHandler DEFAULTS = new NetworkConfigDefaultHandler() {
        @Override
        public void applyDefaults(NetworkConfig config) { }
    };

    @Bean
    public  NetworkConfig getThingIngCOAPNetConfig(){
        return NetworkConfig.createWithFile(CONFIG_FILE, CONFIG_HEADER, DEFAULTS);
    }

    @Bean
    public CoapResource getDefaultResource(){
        return ThingIngCOAPRootResource.build();
    }

    private static class ThingIngCOAPRootResource extends CoapResource {

        private static final String msg  = new StringBuilder().append("Help Message").append("\n").toString();
        private static final CoapResource rootResource;

        static { rootResource = new ThingIngCOAPRootResource(); }

        public static CoapResource build(){
            return rootResource;
        }

        private  ThingIngCOAPRootResource() {
            super("");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond(CoAP.ResponseCode.CONTENT, msg);
        }

        public List<Endpoint> getEndpoints(ThingIngCOAPServer server) {
            return server.getEndpoints();
        }
    }

}
