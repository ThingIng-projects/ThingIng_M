package com.thinging.project.service;

import com.thinging.project.client.EndpointManager;
import com.thinging.project.response.UserAccountDto;
import com.thinging.project.security.utils.ThingIngGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private PasswordEncoder bCryptEncoder;
    private EndpointManager endpointManager;

    public JwtUserDetailsService(PasswordEncoder bCryptEncoder, EndpointManager endpointManager) {
        this.bCryptEncoder = bCryptEncoder;
        this.endpointManager = endpointManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccountDto user = null;
        try {
            user = endpointManager.userServiceGetUserByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new ThingIngGrantedAuthority(user.getRole()));

        return new User(user.getEmail(),
                bCryptEncoder.encode(user.getPassword()),
                authorities);
    }

}