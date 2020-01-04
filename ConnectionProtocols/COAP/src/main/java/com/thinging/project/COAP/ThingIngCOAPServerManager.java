package com.thinging.project.COAP;

import com.thinging.project.COAP.server.ThingIngCOAPServer;
import com.thinging.project.resources.ThingsControllerAbstractResource;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.core.server.resources.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ThingIngCOAPServerManager {

    private ThingIngCOAPServer thingIngCOAPServer;
    private MessageDeliverer messageDeliverer;

    public ThingIngCOAPServerManager(ThingIngCOAPServer thingIngCOAPServer, MessageDeliverer messageDeliverer){
            this.thingIngCOAPServer = thingIngCOAPServer;
            this.messageDeliverer   = messageDeliverer;
            thingIngCOAPServer.setMessageDeliverer(this.messageDeliverer);
    }

    public void addResource(CoapResource serverResource){
            thingIngCOAPServer.add(serverResource);
    }
    public void addChildResource(String resource){

        thingIngCOAPServer.add(new ThingsControllerAbstractResource(resource));

//        thingIngCOAPServer.getRoot().add(resource);
    }

    public void StartCOAPServer(){
       thingIngCOAPServer.start();
    }

    public void stopCOAPServer() {
        thingIngCOAPServer.stop();
    }


    public ThingIngCOAPServer getThingIngCOAPServer() {
        return thingIngCOAPServer;
    }
}
