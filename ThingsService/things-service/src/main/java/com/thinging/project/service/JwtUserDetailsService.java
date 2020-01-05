package com.thinging.project.service;

import com.thinging.project.dto.UserAccountDto;
import com.thinging.project.repository.UserAccountRepository;
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
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccountDto user = new UserAccountDto();
        try {
            user.mapFromUser(userAccountRepository.findByEmail(username).get());
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getEmail(),
                bCryptEncoder.encode(user.getPassword()),
                new ArrayList<>());
    }


}