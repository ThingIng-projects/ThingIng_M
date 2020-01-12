package com.thinging.project.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinging.project.eventManagement.dto.ThingIngAction;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class ThingIngActionExecutor {

    private ObjectMapper objectMapper;

    public ThingIngActionExecutor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        HttpClient httpClient = HttpClients.custom().disableCookieManagement().build();
        Unirest.config().httpClient(httpClient);
    }

    public HttpResponse<?> perform(ThingIngAction action) throws JsonProcessingException {

        HttpResponse<String> response = null;
        switch (action.getRequestMethod()){

            case GET:
                response = Unirest.get(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams()).asString();
                break;
            case POST:
                response = Unirest.post(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams())
                        .body(objectMapper.writeValueAsString(action.getRequestBody())).asString();
                System.out.println(response.getBody());
                break;
            case PUT:
                response = Unirest.put(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams())
                        .body(objectMapper.writeValueAsString(action.getRequestBody())).asString();
                break;
            case PATCH:
                response = Unirest.patch(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams())
                        .body(objectMapper.writeValueAsString(action.getRequestBody())).asString();
                break;
            case DELETE:
                response = Unirest.delete(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams())
                        .body(objectMapper.writeValueAsString(action.getRequestBody())).asString();
                break;
            case HEAD:
                response = Unirest.head(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams()).asString();
                break;
            case OPTIONS:
                response = Unirest.options(String.format(action.getRequestUrl()))
                        .headers(action.getRequestHeaders())
                        .routeParam(action.getRequestParams()).asString();
            default:
                break;
        }

        return response;
    }


}
