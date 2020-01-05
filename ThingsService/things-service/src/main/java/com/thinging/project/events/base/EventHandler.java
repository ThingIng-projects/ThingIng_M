package com.thinging.project.events.base;


import com.thinging.project.response.ThingIngEventData;

public interface EventHandler<T extends ThingIngEventData> {

    void handle(T event);

}
