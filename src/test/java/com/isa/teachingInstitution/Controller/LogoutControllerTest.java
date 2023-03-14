package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Service.LogoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogoutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LogoutService logoutService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private LogoutController logoutController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogout() {
        String authToken = "Bearer token123";
        String token = authToken.substring(7);

        when(request.getHeader("auth-token")).thenReturn(authToken);
        when(logoutService.invalidateToken(token)).thenReturn("successfully logout");

        String result = logoutController.logout();

        assertEquals("successfully logout", result);
        verify(logoutService, times(1)).invalidateToken(token);

    }
}