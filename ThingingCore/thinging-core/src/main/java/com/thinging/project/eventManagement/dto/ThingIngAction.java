package com.thinging.project.eventManagement.dto;

import com.thinging.project.eventManagement.type.Function;

public class ThingIngAction {

    private Function type;

    @Override
    public String toString() {
        return "ThingIngAction{" +
                "type=" + type +
                '}';
    }

    public Function getType() {
        return type;
    }

    public void setType(Function type) {
        this.type = type;
    }
}
