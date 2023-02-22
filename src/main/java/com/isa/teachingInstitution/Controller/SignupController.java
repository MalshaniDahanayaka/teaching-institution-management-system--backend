package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Exceptions.UserAlreadyExistsException;
import com.isa.teachingInstitution.Model.Request.SignupRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SignupController {
    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest){

        try {
            signupService.createUser(signupRequest);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("User successfully registered.", HttpStatus.CREATED);
    }

}
