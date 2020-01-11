package com.thinging.project.entity;


import com.thinging.project.eventManagement.type.EventType;
import com.thinging.project.eventManagement.type.ExecutionType;
import com.thinging.project.persistance.EventDataValue;
import com.thinging.project.persistance.EventDataConverter;

import javax.persistence.*;

@Entity
@Table
public class ThingingEvent {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EventType eventType;
    private ExecutionType executionType;

    @Convert(converter = EventDataConverter.class)
    @Column(length = 1024)
    private EventDataValue eventDataValue;

    @ManyToOne
    private ThingingAction action;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ExecutionType getExecutionType() {
        return executionType;
    }

    public void setExecutionType(ExecutionType executionType) {
        this.executionType = executionType;
    }

    public EventDataValue getEventDataValue() {
        return eventDataValue;
    }

    public void setEventDataValue(EventDataValue eventDataValue) {
        this.eventDataValue = eventDataValue;
    }

    public ThingingAction getAction() {
        return action;
    }

    public void setAction(ThingingAction action) {
        this.action = action;
    }
}
