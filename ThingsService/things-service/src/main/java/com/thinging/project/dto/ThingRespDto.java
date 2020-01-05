package com.thinging.project.dto;

public class ThingRespDto {

    private String thingId;
    private String description;


    public ThingRespDto() {
    }

    public ThingRespDto(String thingId, String description) {
        this.thingId = thingId;
        this.description = description;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
