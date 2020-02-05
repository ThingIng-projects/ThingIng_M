package com.thinging.project.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageProperties {

    private static final String location = String.format("%s%s",System.getProperty("user.home"),"/.ThingIng");

    public String getLocation() {
        return location;
    }

}
