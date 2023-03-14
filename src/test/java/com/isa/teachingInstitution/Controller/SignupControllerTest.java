package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Exceptions.UserAlreadyExistsException;
import com.isa.teachingInstitution.Model.Request.SignupRequest;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Service.SignupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignupControllerTest {

    @InjectMocks
    SignupController signupController;

    @Mock
    SignupService signupService;

    @Test
    void test_SignupSuccess() throws UserAlreadyExistsException {

        SignupRequest signupRequest = new SignupRequest(
                "Malshani",
                "Dahanayaka",
                "mmd",
                "mmd@gmail.com",
                "1111",
                "Student",
                "SE-2018-011"
        );

        User registeredUser = new User();
        registeredUser.setFirstName("Malshani");
        registeredUser.setLastName("Dahanayaka");
        registeredUser.setUsername("mmd");
        registeredUser.setEmail("mmd@gmail.com");
        registeredUser.setPassword("1111");
        registeredUser.setRole("Student");


        when(signupService.createUser(signupRequest)).thenReturn(registeredUser);

        ResponseEntity<String> response = signupController.signup(signupRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Malshani successfully registered.", response.getBody());

        verify(signupService, times(1)).createUser(signupRequest);
    }


    @Test
    void test_SignupUserAlreadyExists() throws UserAlreadyExistsException {

        SignupRequest signupRequest = new SignupRequest(
                "Malshani",
                "Dahanayaka",
                "mmd",
                "mmd@gmail.com",
                "1111",
                "Student",
                "SE-2018-011"
        );

        doThrow(UserAlreadyExistsException.class).when(signupService).createUser(signupRequest);
        ResponseEntity<String> response = signupController.signup(signupRequest);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(signupService, times(1)).createUser(signupRequest);
    }
}
