package com.thinging.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThingIngSystemUserConfig {

    @Value("${thinging.system.user.name}")
    private String systemUserEmail;

    @Value("${thinging.system.user.password}")
    private String systemUserPassword;


    public String getSystemUserEmail() {
        return systemUserEmail;
    }

    public String getSystemUserPassword() {
        return systemUserPassword;
    }
}
