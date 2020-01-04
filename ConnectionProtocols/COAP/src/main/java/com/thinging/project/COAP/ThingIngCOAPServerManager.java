package com.thinging.project.COAP;

import com.thinging.project.COAP.server.ThingIngCOAPServer;
import com.thinging.project.exceptions.COAPServerNotStartedException;
import com.thinging.project.exceptions.COAPServerStartedException;
import com.thinging.project.exceptions.utils.ErrorCode;
import com.thinging.project.resources.ThingIngCOAPAbstractResource;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.springframework.http.ResponseEntity;
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
        if (!thingIngCOAPServer.isRunning())
            throw new COAPServerNotStartedException("Server not started");
        thingIngCOAPServer.add(new ThingIngCOAPAbstractResource(resource));
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


    public ThingIngCOAPServer getThingIngCOAPServer() {
        return thingIngCOAPServer;
    }
}
