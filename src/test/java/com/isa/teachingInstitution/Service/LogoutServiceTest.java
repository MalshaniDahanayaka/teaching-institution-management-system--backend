package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Auth.JwtRequestFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogoutServiceTest {

    @Mock
    private JwtRequestFilter jwtRequestFilter;

    @InjectMocks
    private LogoutService logoutService;

    @Test
    public void testInvalidateToken() {
        String token = "testToken";
        when(jwtRequestFilter.invalidateToken(token)).thenReturn("successfully logout");

        String result = logoutService.invalidateToken(token);

        assertEquals("successfully logout", result);
        verify(jwtRequestFilter, times(1)).invalidateToken(token);
    }
}
