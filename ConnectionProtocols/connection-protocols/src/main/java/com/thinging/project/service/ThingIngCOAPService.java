package com.thinging.project.service;

import com.thinging.project.COAP.server.ThingIngCOAPServer;
import com.thinging.project.exceptions.COAPResourceNotExistsException;
import com.thinging.project.exceptions.COAPServerNotStartedException;
import com.thinging.project.exceptions.COAPServerStartedException;
import com.thinging.project.resources.ThingIngCOAPAbstractResource;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.core.server.resources.Resource;
import org.springframework.stereotype.Service;

@Service
public class ThingIngCOAPService {

    private ThingIngCOAPServer thingIngCOAPServer;
    private MessageDeliverer messageDeliverer;

    public ThingIngCOAPService(ThingIngCOAPServer thingIngCOAPServer, MessageDeliverer messageDeliverer){
            this.thingIngCOAPServer = thingIngCOAPServer;
            this.messageDeliverer   = messageDeliverer;
            thingIngCOAPServer.setMessageDeliverer(this.messageDeliverer);
    }

    public void StartCOAPServer(){
        if (thingIngCOAPServer.isRunning())
            throw new COAPServerStartedException("Server already started");
       thingIngCOAPServer.start();
    }

    public void stopCOAPServer() {
        if (!thingIngCOAPServer.isRunning())
            throw new COAPServerNotStartedException("Server not started");
        thingIngCOAPServer.stop();
    }

    public void removeChildResource(String resource) {
        Resource resourceToDelete = thingIngCOAPServer.getRoot().getChild(resource);
        if( resourceToDelete == null)
            throw new COAPResourceNotExistsException(String.format("Resource with name %s not registered",resource));
        thingIngCOAPServer.getRoot().delete(resourceToDelete);
    }

    public void addChildResource(String resource){
        if (!thingIngCOAPServer.isRunning())
            throw new COAPServerNotStartedException("Server not started");
        thingIngCOAPServer.add(new ThingIngCOAPAbstractResource(resource));
    }

    public ThingIngCOAPServer getThingIngCOAPServer() {
        return thingIngCOAPServer;
    }


}
