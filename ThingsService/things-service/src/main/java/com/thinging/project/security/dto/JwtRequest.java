package com.thinging.project.security.dto;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private String username;
    private String password;

    public JwtRequest(String username, String password) {
        this.username= username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}