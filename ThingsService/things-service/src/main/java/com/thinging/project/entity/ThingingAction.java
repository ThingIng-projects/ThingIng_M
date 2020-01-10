package com.thinging.project.entity;

import com.thinging.project.persistance.HstoreUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;
import java.util.Map;


@Entity
@Table
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class ThingingAction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestUrl;
    private RequestMethod requestMethod;

    @Type(type="hstore")
    @Column( name = "request_params", columnDefinition = "hstore")
    private Map<String, Object> requestParams;

    @Type(type="hstore")
    @Column(name = "request_headers", columnDefinition = "hstore")
    private Map<String, String> requestHeaders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}
