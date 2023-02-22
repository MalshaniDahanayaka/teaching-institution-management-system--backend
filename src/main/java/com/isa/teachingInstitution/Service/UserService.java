package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private CourseRepository courseRepository;
    public List<Course> getAllCourses(){

        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve courses from the repository.");
        }
    }
}
