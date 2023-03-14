package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Request.CourseEnrollRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.StudentCourseEnrollment;
import com.isa.teachingInstitution.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student(
                "Malshani",
                "Dahanayaka",
                "mmd",
                "1111",
                "mmd@gmail.com",
                "Student",
                "SE-2018-011");
    }

    @Test
    public void test_GetProfileData_returnsStudentWithHttpStatusOk() {
        when(studentService.getProfileData("john")).thenReturn(student);

        Object responseEntity = studentController.getProfileData("john");
        ResponseEntity<?> response = ResponseEntity.ok(responseEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student,response.getBody());
    }

    @Test
    public void test_GetProfileData_returnsNotFoundWhenStudentNotFound() {
        when(studentService.getProfileData("jane")).thenReturn(null);

        Object responseEntity = studentController.getProfileData("jane");
        ResponseEntity<?> response = ResponseEntity.notFound().build();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void test_EnrollToCourse_returnsEnrollmentWithHttpStatusOk() {
        CourseEnrollRequest courseEnrollRequest = new CourseEnrollRequest("mmd", "SENG-20-1");

        StudentCourseEnrollment enrollment = new StudentCourseEnrollment("mmd","SENG-20-1");

        when(studentService.enrollToCourse(courseEnrollRequest, "mmd")).thenReturn(enrollment);

        Object responseEntity = studentController.enrollToCourse(courseEnrollRequest, new User("mmd", "", new ArrayList<>()));
        ResponseEntity<?> response = ResponseEntity.ok(responseEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enrollment, response.getBody());
    }


    @Test
    public void test_EnrollToCourse_returnsBadRequestWhenCourseEnrollRequestInvalid() {
        CourseEnrollRequest courseEnrollRequest = new CourseEnrollRequest(null, null);

        when(studentService.enrollToCourse(courseEnrollRequest, "john90")).thenReturn(null);

        Object responseEntity = studentController.enrollToCourse(courseEnrollRequest, new User("john90", "", new ArrayList<>()));
        ResponseEntity<?> response = ResponseEntity.badRequest().build();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }




}