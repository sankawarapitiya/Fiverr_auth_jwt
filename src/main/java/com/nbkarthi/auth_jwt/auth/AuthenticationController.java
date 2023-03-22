package com.nbkarthi.auth_jwt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private  AuthenticationService service;


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<? > authenticate(
            @RequestBody AuthenticationRequest request
    ){

        Map<String , String > server_token = new HashMap<>();
        server_token.put("server_token", service.authenticate(request));
        return  ResponseEntity.ok(server_token);
    }







}
