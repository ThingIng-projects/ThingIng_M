package com.thinging.project.client.config;

import org.springframework.context.annotation.Configuration;

public class ThingIngEndpointConfiguration {

    private static final ThingIngEndpointConfiguration THING_ING_ENDPOINT_CONFIGURATION;

    static {
        THING_ING_ENDPOINT_CONFIGURATION = new ThingIngEndpointConfiguration();
    }
    private ThingIngEndpointConfiguration(){ }


    /** EVENT_MANAGEMENT service**/
    public static final int EVENT_MANAGEMENT_SERVICE_PORT = 8083;
    public static final String EVENT_MANAGEMENT_HOST = "http://localhost";
    public static final String EVENT_MANAGEMENT_MQTT_HANDLER = "/api/events/mqtt/handler";

    /** SECURITY service**/
    public static final int USER_SERVICE_PORT = 8089;
    public static final String USER_SERVICE_HOST = "http://localhost";
    public static final String USER_SERVICE_ENDPOINT = "/user";

    /** MQTT service**/
    public static final int MQTT_SERVICE_PORT = 9003;
    public static final String MQTT_SERVICE_HOST ="http://localhost";
    public static final String MQTT_EVENTS_CREATE = "/api/mqtt/event/new";

}
