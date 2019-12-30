package com.thinging.project.COAP.config;

import com.thinging.project.COAP.server.ThingIngCOAPServer;
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
        return new ThingIngCOAPRootResource();
    }


    private static class ThingIngCOAPRootResource extends CoapResource {

        private final String msg;
        public ThingIngCOAPRootResource() {
            super("");
            StringBuilder builder = new StringBuilder()
                    .append("Help Message").append("\n");
            msg = builder.toString();
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
