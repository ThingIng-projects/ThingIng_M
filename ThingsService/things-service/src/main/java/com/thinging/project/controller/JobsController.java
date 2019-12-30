package com.thinging.project.controller;

import com.thinging.project.service.JobService;
import com.thinging.project.security.utils.consts.JobExecutionType;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("api/things/jobs")
@Api("Jobs management")
public class JobsController extends AbstractController{

    private JobService jobService;

    public JobsController(JobService jobService, Validator validator) {
        super(validator);
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<?> getJobs(
            @RequestHeader(value = "Authorization", required = false) String token){
        return respondOK(jobService.getAll());
    }

    @GetMapping("/{job_name}")
    public ResponseEntity<?> getJob(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("job_name") String jobName){

        return respondOK(jobService.get(jobName));
    }

    @PostMapping
    public ResponseEntity<?> createJob(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam(value = "Job-Name") String jobName,
            @RequestParam(value="Execution-Type", defaultValue = "ONE_TIME_EXECUTION") JobExecutionType executionType,
            @RequestParam(value = "File-Path") String jsonDocumentPath,
            @RequestParam(value = "Description",required = false) String description){

        return respondCreated(jobService.create(jobName,description,executionType,jsonDocumentPath));
    }


    @DeleteMapping("/{id}")
    public void deleteJob(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("id") String id){
        jobService.delete(id);
        respondEmpty();
    }


}
