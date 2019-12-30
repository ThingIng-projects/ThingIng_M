package com.thinging.project.eventManagement.type;

import com.thinging.project.eventManagement.Request.*;

public enum ServiceType {
    THINGS_SERVICE,
    DATA_SERVICE,
    COAP_SERVICE,
    MQTT_SERVICE,
    CUSTOM_SERVICE;

    public static String eventDataAsString(Object eventData, ServiceType serviceType) {

        System.out.println(eventData.getClass());

        switch (serviceType){
            case COAP_SERVICE:
                if(!(eventData instanceof COAPEventRequest)) throw new RuntimeException("Type Exception");
                return ((COAPEventRequest) eventData).toValueString();
            case MQTT_SERVICE:
                if(!(eventData instanceof MQTTEventRequest)) throw new RuntimeException("Type Exception");
                return ((MQTTEventRequest) eventData).toValueString();
            case THINGS_SERVICE:
                if(!(eventData instanceof ThingsEventRequest)) throw new RuntimeException("Type Exception");
                return ((ThingsEventRequest) eventData).toValueString();
            case DATA_SERVICE:
                if(!(eventData instanceof DataServiceEventRequest)) throw new RuntimeException("Type Exception");
                return ((DataServiceEventRequest) eventData).toValueString();
            case CUSTOM_SERVICE:
                if(!(eventData instanceof CustomEventRequest)) throw new RuntimeException("Type Exception");
                return ((CustomEventRequest) eventData).toValueString();
            default:
                throw  new IllegalArgumentException("Event type wrong");

        }
    }

    public static EventRequest stringAsEventData(String value, String type) {

        switch (Enum.valueOf(ServiceType.class,type)){

            case COAP_SERVICE:
                COAPEventRequest coapEventRequest = new COAPEventRequest();
                return coapEventRequest.valueOf(value);
            case DATA_SERVICE:
                DataServiceEventRequest dataServiceEventRequest = new DataServiceEventRequest();
                return dataServiceEventRequest.valueOf(value);
            case MQTT_SERVICE:
                MQTTEventRequest mqttEventRequest = new MQTTEventRequest();
                return mqttEventRequest.valueOf(value);
            case CUSTOM_SERVICE:
                CustomEventRequest customEventRequest = new CustomEventRequest();
                return  customEventRequest.valueOf(value);
            case THINGS_SERVICE:
                ThingsEventRequest thingsEventRequest = new ThingsEventRequest();
                return thingsEventRequest.valueOf(value);
            default:
                throw new IllegalArgumentException("Event type wrong");
        }

    }
}
