package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Repository.CourseRepository;
import com.isa.teachingInstitution.Repository.ManagementTeamRepository;
import com.isa.teachingInstitution.Service.ManagementTeamService;
import com.isa.teachingInstitution.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/managementTeam")
public class ManagementTeamController {
    @Autowired
    private ManagementTeamService managementTeamService;

    @GetMapping("/allCourses")
    public List<Course> getAllCourses(){

        return managementTeamService.getAllCourses();
    }
    @GetMapping("/allStudents")
    public List<Student> getAllStudents(){
        return managementTeamService.getAllStudents();
    }

    @GetMapping("/allTeachers")
    public List<Teacher> getAllTeachers(){
        return managementTeamService.getAllTeachers();
    }
    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return managementTeamService.getAllUsers();
    }
}
