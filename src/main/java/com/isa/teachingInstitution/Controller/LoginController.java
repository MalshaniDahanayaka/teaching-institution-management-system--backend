package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Request.JwtRequest;
import com.isa.teachingInstitution.Model.Response.JwtResponse;
import com.isa.teachingInstitution.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return loginService.createJwtToken(jwtRequest);
    }

}
