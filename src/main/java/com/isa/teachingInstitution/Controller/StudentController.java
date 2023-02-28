package com.isa.teachingInstitution.Controller;
import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Request.CourseEnrollRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.StudentCourseEnrollment;
import com.isa.teachingInstitution.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


    @PostMapping("/enroll/course")
    public StudentCourseEnrollment enrollToCourse(@RequestBody CourseEnrollRequest courseEnrollRequest,
                                                  @AuthenticationPrincipal UserDetails userDetails){

        return studentService.enrollToCourse(courseEnrollRequest, userDetails.getUsername());
    }

    @GetMapping("/enrolled/courses/{userName}")
    public List<Course> getEnrolledCourses(@PathVariable String userName,
                                               @AuthenticationPrincipal UserDetails userDetails){

        List<Course> courses =  studentService.getEnrolledCourses(userName);

        return courses;
    }

}

