package com.isa.teachingInstitution.Controller;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.isa.teachingInstitution.Model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Service.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_GetAllCoursesReturnsListOfCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("SENG-10-1", "Introduction to Computer Science",
                "This is for test","Monday",new Teacher()));
        courses.add(new Course("SENG-20-1", "Data Structures and Algorithms",
                "This is for test","Tuesday",new Teacher()));

        when(userService.getAllCourses()).thenReturn(courses);

        ResponseEntity<List<Course>> response = userController.getAllCourses();

        assertEquals(courses, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void test_GetAllCoursesReturnsInternalServerErrorOnException() {
        when(userService.getAllCourses()).thenThrow(new RuntimeException());

        ResponseEntity<List<Course>> response = userController.getAllCourses();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void test_CourseEnrollmentsReturnsNumberOfEnrollments() {
        String courseId = "SENG-10-1";
        int expectedEnrollments = 10;

        when(userService.courseEnrollments(courseId)).thenReturn(expectedEnrollments);

        int result = userController.courseEnrollments(courseId);

        assertEquals(expectedEnrollments, result);
    }
}
