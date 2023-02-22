package com.isa.teachingInstitution.Model.Request;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtRequest {

    private String username;
    private String password;

}
