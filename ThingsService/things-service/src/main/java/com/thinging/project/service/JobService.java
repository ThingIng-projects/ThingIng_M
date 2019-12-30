package com.thinging.project.service;

import com.thinging.project.security.dto.JobRespDto;
import com.thinging.project.security.entity.Job;
import com.thinging.project.repository.JobRepository;
import com.thinging.project.security.utils.consts.JobExecutionType;
import com.thinging.project.security.utils.consts.JobStatus;
import com.thinging.project.security.utils.parser.DataParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private JobRepository jobRepository;
    private DataParser dataParser;


    public JobService(JobRepository jobRepository,DataParser dataParser) {
        this.jobRepository = jobRepository;
        this.dataParser = dataParser;
    }

    public JobRespDto create(String name, String description,
                             JobExecutionType executionType, String jsonDocumentPath){

        if(jobRepository.existsByName(name)) return null;

        Job job = new Job();

        job.setName(name);
        job.setDescription(description);
        job.setExecutionType(executionType);
        job.setJobStatus(JobStatus.NOT_EXECUTED);
        job.setJsonDocumentPath(jsonDocumentPath);
        job.setThingGroups(new ArrayList<>());
        job.setThings(new ArrayList<>());

        return dataParser.jobToRespDto(jobRepository.save(job));
    }

    public JobRespDto update(String jobName, Job job){

        Optional<Job> jobToFind = jobRepository.findByName(jobName);

        if(jobToFind.isEmpty()) return null;

        String description = job.getDescription()==null?job.getDescription():"";
        String name = job.getName()==null?jobToFind.get().getName():job.getName();

        if(!name.equals(jobToFind.get().getName())) {
            if (jobRepository.existsByName(name)) return null;
        }

        jobToFind.get().setDescription(description);
        jobToFind.get().setName(name);
        jobToFind.get().setJobStatus(job.getJobStatus());
        jobToFind.get().setExecutionType(job.getExecutionType());
        jobToFind.get().setJsonDocumentPath(job.getJsonDocumentPath());

        return dataParser.jobToRespDto(jobRepository.save(jobToFind.get()));
    }

    public JobRespDto get(String jobName){

        Optional<Job> job = jobRepository.findByName(jobName);
        if(!job.isPresent()) return null;

        return dataParser.jobToRespDto(job.get());
    }

    public List<JobRespDto> getAll(){

        List<JobRespDto> jobs = new ArrayList<>();

        jobRepository.findAll().stream().forEach(job -> jobs.add(dataParser.jobToRespDto(job)));

        return jobs;
    }

    public void delete(String jobName){

        Optional<Job> job = jobRepository.findByName(jobName);

        if(job.isPresent()){
            jobRepository.delete(job.get());
            // add logic
        }

    }
}
