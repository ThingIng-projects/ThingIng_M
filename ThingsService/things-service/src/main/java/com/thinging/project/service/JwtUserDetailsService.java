package com.thinging.project.service;

import com.thinging.project.response.UserAccountDto;
import com.thinging.project.security.utils.ThingIngGrantedAuthority;
import com.thinging.project.service.sys.UserAccountService;
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
    private UserAccountService userAccountService;

    public JwtUserDetailsService(PasswordEncoder bCryptEncoder, UserAccountService userAccountService) {
        this.bCryptEncoder = bCryptEncoder;
        this.userAccountService = userAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccountDto user = null;
        try {
            user = userAccountService.getUserByEmail(username);
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