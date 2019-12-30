package com.thinging.project.eventManagement.Request;

import com.thinging.project.eventManagement.type.Function;

public class ThingIngActionRequest {

    private Function type;

    @Override
    public String toString() {
        return "ThingIngActionRequest{" +
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
