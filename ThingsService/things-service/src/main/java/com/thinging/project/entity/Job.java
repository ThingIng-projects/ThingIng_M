package com.thinging.project.entity;

import com.thinging.project.utils.consts.JobExecutionType;
import com.thinging.project.utils.consts.JobStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private JobExecutionType executionType;

    @Column(nullable = false)
    private String jsonDocumentPath;

    @Column(nullable = false)
    private JobStatus jobStatus;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Thing> things;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ThingGroup> thingGroups;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobExecutionType getExecutionType() {
        return executionType;
    }

    public void setExecutionType(JobExecutionType executionType) {
        this.executionType = executionType;
    }

    public String getJsonDocumentPath() {
        return jsonDocumentPath;
    }

    public void setJsonDocumentPath(String jsonDocumentPath) {
        this.jsonDocumentPath = jsonDocumentPath;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public List<ThingGroup> getThingGroups() {
        return thingGroups;
    }

    public void setThingGroups(List<ThingGroup> thingGroups) {
        this.thingGroups = thingGroups;
    }
}
