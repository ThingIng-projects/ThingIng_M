package com.thinging.project.security.utils.parser;

import com.thinging.project.security.dto.GroupRespDto;
import com.thinging.project.security.dto.JobRespDto;
import com.thinging.project.security.dto.ThingRespDto;
import com.thinging.project.security.entity.Job;
import com.thinging.project.security.entity.Thing;
import com.thinging.project.security.entity.ThingGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataParser {

    public ThingRespDto thingToRespDto(Thing thing){
        return new ThingRespDto(thing.getThingId(), thing.getDescription());
    }

    public GroupRespDto groupToDto(ThingGroup group) {

        List<ThingRespDto> thingRespDtos =  new ArrayList<>();

        group.getThings().stream().forEach(thing -> thingRespDtos.add(this.thingToRespDto(thing)));

        return new GroupRespDto(group.getName(), group.getDescription(),thingRespDtos);
    }

    public JobRespDto jobToRespDto(Job job){

        List<ThingRespDto> thingRespDtos =  new ArrayList<>();
        List<GroupRespDto> groupRespDtos =  new ArrayList<>();

        job.getThings().stream().forEach(thing -> thingRespDtos.add(this.thingToRespDto(thing)));
        job.getThingGroups().stream().forEach(group -> groupRespDtos.add(this.groupToDto(group)));

        return new JobRespDto(job.getName(),job.getDescription(),
                job.getExecutionType(),job.getJsonDocumentPath(),
                job.getJobStatus(),thingRespDtos,groupRespDtos);
    }
}
