package com.thinging.project.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinging.project.client.config.ThingIngEndpointConfiguration;
import com.thinging.project.errors.utils.ErrorCode;
import com.thinging.project.errors.utils.ErrorResponse;
import com.thinging.project.request.ThingIngEventDataRequest;
import com.thinging.project.response.UserAccountDto;
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


    public ErrorResponse mqttRegisterEvent(String token, ThingIngEventDataRequest newEvent) throws JsonProcessingException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.MQTT_SERVICE_HOST,
                ThingIngEndpointConfiguration.MQTT_SERVICE_PORT,ThingIngEndpointConfiguration.MQTT_EVENTS_SET);

        HttpResponse<String> response = Unirest.post(requestURL)
                .header("content-type", "application/json")
                .header("Authorization", token)
                .body(objectMapper.writeValueAsString(newEvent)).asString();
        if(response.isSuccess())
            return new ErrorResponse(ErrorCode.STATUS_OK, response.getBody());

        System.out.println(response.getBody());

        return new ErrorResponse(ErrorCode.MQTT_ERROR,response.getBody());
    }

    public ErrorResponse coapRegisterEvent(String token, ThingIngEventDataRequest requestData) throws JsonProcessingException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.COAP_SERVICE_HOST,
                ThingIngEndpointConfiguration.COAP_SERVICE_PORT,ThingIngEndpointConfiguration.COAP_EVENTS_CREATE);

        HttpResponse<String> response = Unirest.post(requestURL)
                .header("content-type", "application/json")
                .header("Authorization", token)
                .body(objectMapper.writeValueAsString(requestData)).asString();
        if(response.isSuccess())
            return new ErrorResponse(ErrorCode.STATUS_OK, response.getBody());

        System.out.println(response.getBody());

        return new ErrorResponse(ErrorCode.COAP_ERROR,response.getBody());
    }
}
