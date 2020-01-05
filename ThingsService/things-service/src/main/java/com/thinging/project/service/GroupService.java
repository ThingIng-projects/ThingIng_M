package com.thinging.project.service;

import com.thinging.project.dto.GroupRespDto;
import com.thinging.project.entity.ThingGroup;
import com.thinging.project.repository.GroupRepository;
import com.thinging.project.utils.parser.DataParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private GroupRepository groupRepository;
    private DataParser dataParser;

    public GroupService(GroupRepository groupRepository, DataParser dataParser) {
        this.groupRepository = groupRepository;
        this.dataParser = dataParser;
    }

    public GroupRespDto create(String name, String description){

        if(groupRepository.existsByName(name)) return null;

        ThingGroup group = new ThingGroup();
        group.setName(name);
        group.setDescription(description);
        group.setThings(new ArrayList<>());
        group.setJobs(new ArrayList<>());

        return dataParser.groupToDto(groupRepository.save(group));
    }

    public GroupRespDto get(String groupName){

        Optional<ThingGroup> thingGroup = groupRepository.findByName(groupName);
        if(!thingGroup.isPresent()) return null;

        return dataParser.groupToDto(thingGroup.get());
    }

    public List<GroupRespDto> getAll(){

        List<GroupRespDto> groups = new ArrayList<>();

        groupRepository.findAll().stream().forEach(group -> groups.add(dataParser.groupToDto(group)));

        return groups;
    }

    public void delete(String groupName){

        Optional<ThingGroup> thingGroup = groupRepository.findByName(groupName);

        if(thingGroup.isPresent()){
            groupRepository.delete(thingGroup.get());
        }

    }

}
