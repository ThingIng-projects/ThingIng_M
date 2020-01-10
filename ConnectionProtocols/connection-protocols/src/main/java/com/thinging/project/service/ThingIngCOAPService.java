package com.thinging.project.service;

import com.thinging.project.COAP.server.ThingIngCOAPServer;

import com.thinging.project.errors.*;
import com.thinging.project.eventManagement.dto.COAPEventData;
import com.thinging.project.eventManagement.request.COAPEventRequest;
import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ServiceType;
import com.thinging.project.request.COAPEventDataRequest;
import com.thinging.project.resources.ThingIngCOAPAbstractResource;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.core.server.resources.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public void removeEventHandler(String resourceDeleteFrom, String token) {

        ThingIngCOAPAbstractResource resource = (ThingIngCOAPAbstractResource)thingIngCOAPServer.getRoot().getChild(resourceDeleteFrom);

        if(resource==null)
            throw new COAPResourceNotExistsException(String.format("resource in name %s not exists", resourceDeleteFrom));

        resource.removeEvent();
    }

    public COAPEventRequest addEventHandler(COAPEventDataRequest coapEventDataRequest, String token) {

        if(coapEventDataRequest.getEventType() != EventType.SYSTEM)
            throw new ServiceTypeException(String.format("expected - %s but received %s", ServiceType.MQTT_SERVICE,coapEventDataRequest.getServiceType()));
        if(coapEventDataRequest.getEventType() != EventType.SYSTEM )
            throw new EventTypeException(String.format("expected - %s but received %s",EventType.SYSTEM,coapEventDataRequest.getEventType()));

        ThingIngCOAPAbstractResource resource = (ThingIngCOAPAbstractResource)thingIngCOAPServer.getRoot().getChild(coapEventDataRequest.getCoapEventRequest().getResource());

        if(resource==null) throw new COAPResourceNotExistsException(String.format("resource in name %s not exists",coapEventDataRequest.getCoapEventRequest().getResource()));

        return resource.addEvent(coapEventDataRequest);
    }
}
