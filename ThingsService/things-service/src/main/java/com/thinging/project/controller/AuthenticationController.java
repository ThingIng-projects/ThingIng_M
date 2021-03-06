package com.thinging.project.controller;

import com.thinging.project.response.UserAccountDto;
import com.thinging.project.security.utils.JwtTokenUtil;
import com.thinging.project.security.dto.JwtRequest;
import com.thinging.project.security.dto.JwtResponse;
import com.thinging.project.service.JwtUserDetailsService;
import com.thinging.project.service.sys.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@CrossOrigin
@Api(value="Authentication Management System")
public class AuthenticationController extends AbstractController{

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private UserAccountService userAccountService;

    public AuthenticationController(Validator validator,
                                    AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    JwtUserDetailsService userDetailsService,
                                    UserAccountService userAccountService) {
        super(validator);
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userAccountService = userAccountService;
    }

    @PostMapping(value = "/authenticate")
    @ApiOperation(value = "create authentication token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(userAccountService.getUserByEmail(authenticationRequest.getUsername()));

        return respondOK(new JwtResponse(token));
    }

    @PostMapping(value = "/users/register")
    @ApiOperation(value = "Register new user")
    public ResponseEntity<?> saveUser(
            @RequestHeader("Authorization") String token,
            @RequestBody UserAccountDto user) throws Exception {

        return respondCreated(userAccountService.createUser(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}