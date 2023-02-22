package com.isa.teachingInstitution.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignupRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String role;
    private String userID;


}