package com.thinging.project.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinging.project.client.config.ThingIngEndpointConfiguration;
import com.thinging.project.security.dto.JwtRequest;
import com.thinging.project.security.dto.UserAccountDto;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EndpointManager {

    private ObjectMapper objectMapper;

    public EndpointManager(ObjectMapper mapper) {
        HttpClient httpclient = HttpClients.custom().disableCookieManagement().build();
        Unirest.config().httpClient(httpclient);
    }

    public UserAccountDto userServiceGetUserByEmail(String email) throws IOException {
        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.USER_SERVICE_HOST,
                ThingIngEndpointConfiguration.USER_SERVICE_PORT,ThingIngEndpointConfiguration.USER_SERVICE_ENDPOINT);
        HttpResponse<String> response = Unirest.get(requestURL+"/"+email).asString();

        UserAccountDto userAccountDto;
        if(response.getStatus() == HttpStatus.SC_OK){
            userAccountDto = objectMapper.readValue(response.getBody(),UserAccountDto.class);
        }

        return null;
    }

    public String securityServiceAuthenticate(JwtRequest authenticationRequest){

        String requestURL = String.format("%s:%d%s",ThingIngEndpointConfiguration.SECURITY_SERVICE_HOST,
                ThingIngEndpointConfiguration.SECURITY_SERVICE_PORT,ThingIngEndpointConfiguration.SECURITY_SERVICE_AUTHENTICATE);

        HttpResponse<String> response = Unirest.post(requestURL)
                .header("content-type", "application/json")
                .body(authenticationRequest).asString();

        if(response.getStatus() == HttpStatus.SC_OK){
            return response.getBody();
        }
        return null;
    }


    private Object stringToObject(String content, Class clazz){
        try {
            return objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    private String ObjectToJsonString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
