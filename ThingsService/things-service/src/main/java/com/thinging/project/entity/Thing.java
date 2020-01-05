package com.thinging.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String thingId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ThingGroup thingGroup;

    @Column
    private String description;


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Job> jobs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public ThingGroup getThingGroup() {
        return thingGroup;
    }

    public void setThingGroup(ThingGroup thingGroup) {
        this.thingGroup = thingGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
