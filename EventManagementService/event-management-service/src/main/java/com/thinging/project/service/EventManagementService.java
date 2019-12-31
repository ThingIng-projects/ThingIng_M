package com.thinging.project.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinging.project.entity.ThingingEvent;
import com.thinging.project.request.EventManagementServiceEventDataRequest;
import com.thinging.project.request.ThingIngEventDataRequest;
import com.thinging.project.repository.EventRepository;
import com.thinging.project.utils.DataParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventManagementService {

    private EventRepository eventRepository;
    private EventSenderService eventSenderService;
    private DataParser dataParser;

    public EventManagementService(EventRepository eventRepository, EventSenderService eventSenderService, DataParser dataParser) {
        this.eventRepository = eventRepository;
        this.eventSenderService = eventSenderService;
        this.dataParser = dataParser;
    }

    public EventManagementServiceEventDataRequest createEvent(EventManagementServiceEventDataRequest requestData, String token) throws JsonProcessingException {

        ThingIngEventDataRequest thingIngEventDataRequest = dataParser.eventManagementRequestToThIngIngEvent(requestData);

        eventSenderService.send(thingIngEventDataRequest,token);

        ThingingEvent event = dataParser.thingIngEventRequestToEntity(requestData,null);

        return dataParser.entityToRequestData(eventRepository.save(event));
    }

    public EventManagementServiceEventDataRequest updateEvent(Long id,EventManagementServiceEventDataRequest requestData){

        Optional<ThingingEvent> eventOptional = eventRepository.findById(id);
        if(eventOptional.isEmpty()) throw new IllegalArgumentException("Event with id "+id+ " not exists");

        ThingingEvent event = dataParser.thingIngEventRequestToEntity(requestData,eventOptional.get());

        return dataParser.entityToRequestData(eventRepository.save(event));
    }

    public EventManagementServiceEventDataRequest getEvent(Long id){

        Optional<ThingingEvent> eventOptional = eventRepository.findById(id);
        if(eventOptional.isEmpty()) throw new IllegalArgumentException("Event with id "+id+ " not exists");

        return dataParser.entityToRequestData(eventOptional.get());
    }

    public List<EventManagementServiceEventDataRequest> listOfEvents(Set<Long> idList){

        List<EventManagementServiceEventDataRequest> listOfEventData = new ArrayList<>();
        List<ThingingEvent> events;
        if(idList != null) events = eventRepository.findByIdIn(idList);
        else events = eventRepository.findAll();

        events.stream().forEach(event -> listOfEventData.add(dataParser.entityToRequestData(event)));

        return listOfEventData;
    }

    public void removeEvent(Long id){
        Optional<ThingingEvent> eventOptional = eventRepository.findById(id);
        if(eventOptional.isEmpty()) throw new IllegalArgumentException("Event with id "+id+ " not exists");

        eventRepository.delete(eventOptional.get());
    }



}
