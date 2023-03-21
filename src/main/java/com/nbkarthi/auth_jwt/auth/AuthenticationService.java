package com.nbkarthi.auth_jwt.auth;

import com.nbkarthi.auth_jwt.config.JwtService;
import com.nbkarthi.auth_jwt.config.SignatureAuthenticationToken;
import com.nbkarthi.auth_jwt.model.Role;
import com.nbkarthi.auth_jwt.model.User;
import com.nbkarthi.auth_jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager  authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
       var user = User.builder()
               .firstName(request.getFirstname())
               .lastName(request.getLastname())
               .email(request.getEmail())
               .signature(passwordEncoder.encode(request.getSignature()))
               .role(Role.USER)
               .build();
       userRepository.save(user);

       var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {


        if(validateSignature(request.getSignature())){
            return  jwtService.generateToken("Server Token");
        }else{
            //401; // Unauthorized
        }

    }

    private boolean validateSignature(String signature) {
        return true;
    }


}
