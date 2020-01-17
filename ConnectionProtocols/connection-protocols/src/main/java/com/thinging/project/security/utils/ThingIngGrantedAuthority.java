package com.thinging.project.security.utils;

import com.thinging.project.response.Role;
import org.springframework.security.core.GrantedAuthority;

public class ThingIngGrantedAuthority implements GrantedAuthority {

    private Role role;

    public ThingIngGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return String.valueOf(this.role);
    }
}
