package com.thinging.project.dto;

import com.thinging.project.utils.consts.JobExecutionType;
import com.thinging.project.utils.consts.JobStatus;

import java.util.List;

public class JobRespDto {

    private String name;
    private String description;
    private JobExecutionType executionType;
    private String JsonDocumentPath;
    private JobStatus jobStatus;

    private List<ThingRespDto> attachedThings;
    private List<GroupRespDto> attachedGroups;

    public JobRespDto() {
    }

    public JobRespDto(String name, String description, JobExecutionType executionType, String jsonDocumentPath, JobStatus jobStatus, List<ThingRespDto> attachedThings, List<GroupRespDto> attachedGroups) {
        this.name = name;
        this.description = description;
        this.executionType = executionType;
        JsonDocumentPath = jsonDocumentPath;
        this.jobStatus = jobStatus;
        this.attachedThings = attachedThings;
        this.attachedGroups = attachedGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobExecutionType getExecutionType() {
        return executionType;
    }

    public void setExecutionType(JobExecutionType executionType) {
        this.executionType = executionType;
    }

    public String getJsonDocumentPath() {
        return JsonDocumentPath;
    }

    public void setJsonDocumentPath(String jsonDocumentPath) {
        JsonDocumentPath = jsonDocumentPath;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public List<ThingRespDto> getAttachedThings() {
        return attachedThings;
    }

    public void setAttachedThings(List<ThingRespDto> attachedThings) {
        this.attachedThings = attachedThings;
    }

    public List<GroupRespDto> getAttachedGroups() {
        return attachedGroups;
    }

    public void setAttachedGroups(List<GroupRespDto> attachedGroups) {
        this.attachedGroups = attachedGroups;
    }
}
