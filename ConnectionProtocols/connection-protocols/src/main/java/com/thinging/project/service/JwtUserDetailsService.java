package com.thinging.project.service;

import com.thinging.project.client.EndpointManager;
import com.thinging.project.response.UserAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Autowired
    private EndpointManager endpointManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccountDto user = null;
        try {
            user = endpointManager.userServiceGetUserByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);

        return new User(user.getEmail(),
                bCryptEncoder.encode(user.getPassword()),
                new ArrayList<>());
    }


}