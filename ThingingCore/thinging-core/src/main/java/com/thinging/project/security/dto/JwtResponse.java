package com.thinging.project.security.dto;

import java.io.Serializable;


public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}