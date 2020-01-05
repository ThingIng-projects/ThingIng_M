package com.thinging.project.events.base;


import com.thinging.project.response.ThingIngEventData;

public interface EventExecutor<T extends ThingIngEventData> {

    void execute(T event);

}
