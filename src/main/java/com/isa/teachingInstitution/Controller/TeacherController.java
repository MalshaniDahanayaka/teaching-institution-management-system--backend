package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_Teacher')")
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/profile/{username}")
    public Object getProfileData(@PathVariable String username){
        Teacher teacher = teacherService.getProfileData(username);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).getBody();
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK).getBody();
    }

    @GetMapping("/assigned/course/{userName}")
    public Course teacherCourse(@PathVariable String userName){

        return teacherService.teacherCourse(userName);
    }

    @GetMapping("/enrolled/students-for-course/{courseID}")
    public List<Student> courseEnrolledStudents(@PathVariable String courseID){

        return teacherService.courseEnrolledStudents(courseID);
    }
}

