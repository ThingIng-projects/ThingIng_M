package com.thinging.project.entity;

import com.thinging.project.eventManagement.type.Function;

import javax.persistence.*;


@Entity
@Table
public class ThingingAction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Function function;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
