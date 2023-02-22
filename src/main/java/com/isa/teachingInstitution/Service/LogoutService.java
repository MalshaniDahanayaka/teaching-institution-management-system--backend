package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Auth.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    public void invalidateToken(String token) {
        jwtRequestFilter.invalidateToken(token);
    }
}
