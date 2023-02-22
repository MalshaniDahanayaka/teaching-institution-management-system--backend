package com.isa.teachingInstitution.Controller;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ROLE_Student')")
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/profile/{username}")
    public Object getProfileData(@PathVariable String username){

        Student student = studentService.getProfileData(username);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).getBody();
        }
        return new ResponseEntity<>(student, HttpStatus.OK).getBody();
    }

    }

