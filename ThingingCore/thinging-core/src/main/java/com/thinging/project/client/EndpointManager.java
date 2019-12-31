package com.thinging.project.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinging.project.client.config.ThingIngEndpointConfiguration;
import com.thinging.project.eventManagement.dto.MQTTEventData;
import com.thinging.project.request.ThingIngEventDataRequest;
import com.thinging.project.security.dto.UserAccountDto;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EndpointManager {

    private ObjectMapper objectMapper;

    public EndpointManager(ObjectMapper mapper) {
        HttpClient httpClient = HttpClients.custom().disableCookieManagement().build();
        Unirest.config().httpClient(httpClient);
        this.objectMapper = mapper;
    }

    public UserAccountDto userServiceGetUserByEmail(String email) throws IOException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.USER_SERVICE_HOST,
                ThingIngEndpointConfiguration.USER_SERVICE_PORT,ThingIngEndpointConfiguration.USER_SERVICE_ENDPOINT);

        HttpResponse<String> response = Unirest.get(requestURL+"/"+email).asString();

        return objectMapper.readValue(response.getBody(),UserAccountDto.class);
    }

    public String userServiceCreateUser(UserAccountDto userAccountDto) throws JsonProcessingException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.USER_SERVICE_HOST,
                ThingIngEndpointConfiguration.USER_SERVICE_PORT,ThingIngEndpointConfiguration.USER_SERVICE_ENDPOINT);

        HttpResponse<String> response = Unirest.post(requestURL)
                .header("content-type", "application/json")
                .body(objectMapper.writeValueAsString(userAccountDto)).asString();
        return response.getBody();
    }

    public String mqttSendEvent(String token, MQTTEventData mqttEventData) throws JsonProcessingException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.USER_SERVICE_HOST,
                ThingIngEndpointConfiguration.USER_SERVICE_PORT,ThingIngEndpointConfiguration.USER_SERVICE_ENDPOINT);

        HttpResponse<String> response = Unirest.post(requestURL)
                .header("content-type", "application/json")
                .header("Authorization",token)
                .body(objectMapper.writeValueAsString(mqttEventData)).asString();

        return response.getBody();
    }

    public String mqttRegisterEvent(String token, ThingIngEventDataRequest newEvent) throws JsonProcessingException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.MQTT_SERVICE_HOST,
                ThingIngEndpointConfiguration.MQTT_SERVICE_PORT,ThingIngEndpointConfiguration.MQTT_EVENTS_CREATE);

        HttpResponse<String> response = Unirest.post(requestURL)
                .header("content-type", "application/json")
                .header("Authorization",token)
                .body(objectMapper.writeValueAsString(newEvent)).asString();

        return response.getBody();
    }

}
