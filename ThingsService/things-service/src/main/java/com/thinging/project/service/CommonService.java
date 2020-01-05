package com.thinging.project.service;

import com.thinging.project.dto.GroupRespDto;
import com.thinging.project.dto.JobRespDto;
import com.thinging.project.entity.Job;
import com.thinging.project.entity.Thing;
import com.thinging.project.entity.ThingGroup;
import com.thinging.project.exceptions.NullValueException;
import com.thinging.project.repository.GroupRepository;
import com.thinging.project.repository.JobRepository;
import com.thinging.project.repository.ThingRepository;
import com.thinging.project.utils.consts.JobStatus;
import com.thinging.project.utils.parser.DataParser;
import com.thinging.project.exceptions.DuplicatedValueException;
import com.thinging.project.exceptions.JobNotExistsException;
import com.thinging.project.exceptions.JobStatusInvalidException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommonService {

    private JobRepository jobRepository;
    private ThingRepository thingRepository;
    private GroupRepository groupRepository;
    private DataParser parser;

    public CommonService(JobRepository jobRepository, ThingRepository thingRepository, GroupRepository groupRepository, DataParser parser) {
        this.jobRepository = jobRepository;
        this.thingRepository = thingRepository;
        this.groupRepository = groupRepository;
        this.parser = parser;
    }

    public JobRespDto connectJobToThings(String jobName, Set<String> thingIds){

        Optional<Job> jobToConnect = jobRepository.findByName(jobName);

        if(thingIds == null || thingIds.size() <= 0) throw new NullValueException("Parameter Things cannot be null or empty");
        if(jobToConnect.isEmpty()) throw new JobNotExistsException("Job with name " + jobName + " not exists.");
        if(jobToConnect.get().getJobStatus() == JobStatus.EXECUTED)
            throw new JobStatusInvalidException("Job Status is %s" + jobToConnect.get().getJobStatus());

        List<Thing> thingsToAdd = thingRepository.findByThingIdIn(thingIds);

        if(thingsToAdd.size() <= 0) throw new NullValueException("Cannot find any much things with given names");

        if(thingsToAdd.stream().anyMatch(thing -> thing.getJobs().contains(jobToConnect.get())))
            throw new DuplicatedValueException("One or more things already attached to this job");
        thingsToAdd.forEach(thing -> thing.getJobs().add(jobToConnect.get()));

        jobToConnect.get().getThings().addAll(thingsToAdd);

        return parser.jobToRespDto(jobRepository.save(jobToConnect.get()));
    }

    public JobRespDto connectJobToGroups(String jobName, Set<String> groupNames){

        Optional<Job> jobToConnect = jobRepository.findByName(jobName);

        if(groupNames == null || groupNames.size() <= 0) throw new NullValueException("Parameter Groups cannot be null or empty");
        if(jobToConnect.isEmpty()) throw new JobNotExistsException("Job with name " + jobName + " not exists.");
        if(jobToConnect.get().getJobStatus() == JobStatus.EXECUTED)
            throw new JobStatusInvalidException("Job Status is %s" + jobToConnect.get().getJobStatus());

        List<ThingGroup> groupsAddTo = groupRepository.findByNameIn(groupNames);

        if(groupsAddTo.size() <= 0) throw new NullValueException("CanNot any much groups with given names");
        if(groupsAddTo.stream().anyMatch(group -> group.getJobs().contains(jobToConnect.get())))
            throw new DuplicatedValueException("One or more groups already attached to this job");

        groupsAddTo.forEach(thing -> thing.getJobs().add(jobToConnect.get()));
        jobToConnect.get().getThingGroups().addAll(groupsAddTo);

        return parser.jobToRespDto(jobRepository.save(jobToConnect.get()));

    }

    public GroupRespDto connectThingsToGroup(String groupName, Set<String> thingIds){

        Optional<ThingGroup> groupToFind = groupRepository.findByName(groupName);

        if(thingIds == null || thingIds.size() <= 0) throw new NullValueException("Parameter Things cannot be null or empty");
        if(groupToFind.isEmpty()) throw new JobNotExistsException("Group with name " + groupName + " not exists.");

        List<Thing> thingsToAdd = thingRepository.findByThingIdIn(thingIds);
        if(thingsToAdd.size() <= 0) throw new NullValueException("Cannot find any much things with given names");

        if(groupToFind.get().getThings().stream().anyMatch(thing -> thingsToAdd.contains(thing)))
            throw new DuplicatedValueException("One or more things already attached to this group");
        groupToFind.get().getThings().addAll(thingsToAdd);

        return parser.groupToDto(groupRepository.save(groupToFind.get()));
    }

}
