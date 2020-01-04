package com.thinging.project.COAP;

import com.thinging.project.COAP.server.ThingIngCOAPServer;
import com.thinging.project.resources.ThingIngCOAPAbstractResource;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.springframework.stereotype.Service;

@Service
public class ThingIngCOAPServerManager {

    private ThingIngCOAPServer thingIngCOAPServer;
    private MessageDeliverer messageDeliverer;

    public ThingIngCOAPServerManager(ThingIngCOAPServer thingIngCOAPServer, MessageDeliverer messageDeliverer){
            this.thingIngCOAPServer = thingIngCOAPServer;
            this.messageDeliverer   = messageDeliverer;
            thingIngCOAPServer.setMessageDeliverer(this.messageDeliverer);
    }

    public void addChildResource(String resource){
        thingIngCOAPServer.add(new ThingIngCOAPAbstractResource(resource));
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
