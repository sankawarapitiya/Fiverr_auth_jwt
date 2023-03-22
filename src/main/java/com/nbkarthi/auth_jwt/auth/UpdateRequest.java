package com.nbkarthi.auth_jwt.auth;


import com.nbkarthi.auth_jwt.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    private  Integer id;
    private  String firstname;
    private  String lastname;
    private  String email;
    private  String signature;
    private Role role;

}
