package com.nbkarthi.auth_jwt.auth;

import com.nbkarthi.auth_jwt.config.JwtService;
import com.nbkarthi.auth_jwt.model.Role;
import com.nbkarthi.auth_jwt.model.User;
import com.nbkarthi.auth_jwt.repository.UserRepository;
import com.nbkarthi.auth_jwt.service.SignatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private SignatureService signatureService;
    private final UserRepository userRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager  authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
       var user = User.builder()
               .firstName(request.getFirstname())
               .lastName(request.getLastname())
               .email(request.getEmail())
               .publicKey(passwordEncoder.encode(request.getSignature()))
               .role(Role.USER)
               .build();
       userRepository.save(user);

       var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String    authenticate(AuthenticationRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if(user.isPresent()){
            return jwtService.generateToken(user.get());
        }



//        if(validateSignature(request)){
//            return  jwtService.generateToken("Server Token");
//        }else{
//            //401; // Unauthorized
//        }

        return null;
    }

    private boolean validateSignature(AuthenticationRequest authUser) {

        Optional<User> user =     userRepository.findByEmail(authUser.getEmail());

        return true;
    }

    public  String validateServerKey(ServerTokenRequest serverTokenRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        if(signatureService.isValid(serverTokenRequest.getMessage(),serverTokenRequest.getSignature())){
          return   generateServerToken(serverTokenRequest.getSignature());
        }
        return  null;
    }

    public  String generateServerToken(String key){
       return jwtService.generateServerTokenKey(key);
    }

}
