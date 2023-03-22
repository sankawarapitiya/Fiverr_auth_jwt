package com.nbkarthi.auth_jwt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private  Integer id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  Role role;
}
