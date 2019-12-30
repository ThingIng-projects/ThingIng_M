package com.thinging.project.service;

import com.thinging.project.security.dto.ThingRespDto;
import com.thinging.project.security.entity.Thing;
import com.thinging.project.repository.ThingRepository;
import com.thinging.project.security.utils.parser.DataParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThingService {

    private ThingRepository thingRepository;
    private DataParser dataParser;

    public ThingService(ThingRepository thingRepository, DataParser parser) {
        this.thingRepository = thingRepository;
        this.dataParser = parser;
    }

    public List<ThingRespDto> getThings(){
        List<ThingRespDto> things = new ArrayList<>();
        thingRepository.findAll().stream().forEach(thing -> things.add(dataParser.thingToRespDto(thing)));
        return things;
    }

    public ThingRespDto getThing(Long id){

        Optional<Thing> thingToFind = thingRepository.findById(id);
        if(!thingToFind.isPresent()) return null;

        return dataParser.thingToRespDto(thingToFind.get());
    }

    public ThingRespDto getThing(String thingId){

        Optional<Thing> thingToFind = thingRepository.findByThingId(thingId);
        if(!thingToFind.isPresent()) return null;

        return dataParser.thingToRespDto(thingToFind.get());
    }

    public ThingRespDto addThing(String thingId, String description){

        if(thingRepository.existsByThingId(thingId))
            return null;
        Thing thing = new Thing();
        thing.setThingId(thingId);
        thing.setDescription(description);
        thing.setJobs(new ArrayList<>());

        return dataParser.thingToRespDto(thingRepository.save(thing));
    }


    public void deleteThing(String thingId){

        Optional<Thing> thingToFind = thingRepository.findByThingId(thingId);

        if(thingToFind.isPresent())
            thingRepository.delete(thingToFind.get());

    }
}
