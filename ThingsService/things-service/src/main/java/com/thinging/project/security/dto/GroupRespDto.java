package com.thinging.project.security.dto;

import java.util.List;

public class GroupRespDto {

    private String name;
    private String description;

    private List<ThingRespDto> things;


    public GroupRespDto() {
    }

    public GroupRespDto(String name, String description, List<ThingRespDto> things) {
        this.name = name;
        this.description = description;
        this.things = things;
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

    public List<ThingRespDto> getThings() {
        return things;
    }

    public void setThings(List<ThingRespDto> things) {
        this.things = things;
    }
}
