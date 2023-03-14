package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Request.JwtRequest;
import com.isa.teachingInstitution.Model.Response.JwtResponse;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    LoginService loginService;

    @Test
    public void test_createJwtToken() throws Exception{

        JwtRequest jwtRequest = new JwtRequest("mmd","1111");

        User user = new User(
                "Malshani",
                "Dahanayaka",
                "mmd",
                "mmd@gmail.com",
                "1111",
                "Student");

        String newToken = "new Token";
        String message = "successfully login";

        JwtResponse jwtResponse = new JwtResponse(user,newToken,message);

        when(loginService.createJwtToken(jwtRequest)).thenReturn(jwtResponse);

        JwtResponse responseEntity = loginController.createJwtToken(jwtRequest);
        ResponseEntity<?> response = ResponseEntity.ok(responseEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwtResponse, response.getBody());
    }

}