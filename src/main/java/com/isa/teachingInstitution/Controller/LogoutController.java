package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/logout")
public class LogoutController {
    @Autowired
    private LogoutService logoutService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/user")
    public String logout() {
        String authToken = request.getHeader("auth-token");
        String token = authToken.substring(7);
        return logoutService.invalidateToken(token);

    }


}
