package com.thinging.project.client.config;

import org.springframework.context.annotation.Configuration;

public class ThingIngEndpointConfiguration {

    private static final ThingIngEndpointConfiguration THING_ING_ENDPOINT_CONFIGURATION;

    static {
        THING_ING_ENDPOINT_CONFIGURATION = new ThingIngEndpointConfiguration();
    }
    private ThingIngEndpointConfiguration(){ }

    /** USER service**/
    public static final int USER_SERVICE_PORT = 8082;
    public static final String USER_SERVICE_HOST = "localhost";
    public static final String USER_SERVICE_ENDPOINT = "/user";

    /** EVENT_MANAGEMENT service**/
    public static final int EVENT_MANAGEMENT_SERVICE_PORT = 8083;
    public static final String EVENT_MANAGEMENT_HOST = "localhost";
    public static final String EVENT_MANAGEMENT_ENDPOINT = "/api/events";

    /** SECURITY service**/
    public static final int SECURITY_SERVICE_PORT = 8081;
    public static final String SECURITY_SERVICE_HOST = "localhost";
    public static final String SECURITY_SERVICE_AUTHENTICATE = "/authenticate";
    public static final String SECURITY_SERVICE_REGISTER = "/register";

}
