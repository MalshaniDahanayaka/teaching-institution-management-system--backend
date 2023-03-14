package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Auth.JwtUtil;
import com.isa.teachingInstitution.Model.Request.JwtRequest;
import com.isa.teachingInstitution.Model.Response.JwtResponse;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginService loginService;


    @Test
    void test_CreateJwtTokenWithValidCredentials() throws Exception {
        String username = "mmd";
        String password = "1111";
        JwtRequest jwtRequest = new JwtRequest(username, password);
        User user = new User("Malshani","Dahanayaka","mmd","mmd@gmail.com", "1111", "Student");
        String message = "Successful";

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findById(username)).thenReturn(java.util.Optional.of(user));
        when(jwtUtil.generateToken(any())).thenReturn("abcdef123");


        JwtResponse response = loginService.createJwtToken(jwtRequest);

        assertEquals(user.getUsername(), response.getUserName());
        assertEquals(user.getRole(), response.getUserRole());
        assertEquals("abcdef123", response.getJwtToken());
        assertEquals(message, response.getMessage());

    }


    @Test
    void test_CreateJwtTokenWithInvalidCredentials() throws Exception {
        String username = "mmd";
        String password = "wrongpassword";
        JwtRequest jwtRequest = new JwtRequest(username, password);

        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));

        try {
            authenticationManager.authenticate(any());
        }catch (Exception e){
            JwtResponse response = loginService.createJwtToken(jwtRequest);
            assertNull(response.getUserName());
            assertNull(response.getJwtToken());
            assertEquals("Invalid password", response.getMessage());
        }

    }

    @Test
    void testCreateJwtTokenWithNonexistentUser() throws Exception {
        String username = "nonexistentuser";
        String password = "1111";
        JwtRequest jwtRequest = new JwtRequest(username, password);

        when(authenticationManager.authenticate(any())).thenThrow(new UsernameNotFoundException("Invalid UserName"));

        try {
            authenticationManager.authenticate(any());
        }catch (Exception e){
            JwtResponse response = loginService.createJwtToken(jwtRequest);
            assertNull(response.getUserName());
            assertNull(response.getJwtToken());
            assertEquals("Username not found", response.getMessage());
        }
    }

}

