package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Request.AddCourseRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private Teacher teacher;
    private Course course;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        teacher = new Teacher(
                "Nisha",
                "Karunarathna",
                "nishaK",
                "nisha@gmail.com",
                "1111",
                "Teacher",
                "SE-TE-001");
        course = new Course("SENG-10-1",
                "Software Engineering",
                "This is for test",
                "Monday",
                teacher);
        students = new ArrayList<>();
        students.add(new Student("Bhagya",
                "Dahanayaka",
                "BGD",
                "bhagya@gmail.com",
                "2222",
                "Student",
                "SE-2018-001"));
    }

    @Test
    public void test_GetProfileData_returnsTeacherWithHttpStatusOk() {
        when(teacherService.getProfileData("nishaK")).thenReturn(teacher);

        Object responseEntity = teacherController.getProfileData("nishaK");
        ResponseEntity<?> response = ResponseEntity.ok(responseEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacher, response.getBody());
    }

    @Test
    public void test_GetProfileData_returnsNotFoundWhenTeacherNotFound() {
        when(teacherService.getProfileData("jane")).thenReturn(null);

        Object responseEntity = teacherController.getProfileData("jane");
        ResponseEntity<?> response = ResponseEntity.notFound().build();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void test_TeacherCourse_returnsCourseWithHttpStatusOk() {
        when(teacherService.teacherCourse("nishaK")).thenReturn(course);

        Course response = teacherController.teacherCourse("nishaK");

        assertEquals(course, response);
    }

    @Test
    public void test_CourseEnrolledStudents_returnsListOfStudentsWithHttpStatusOk() {
        when(teacherService.courseEnrolledStudents("SENG-10-1")).thenReturn(students);

        List<Student> response = teacherController.courseEnrolledStudents("SENG-10-1");

        assertEquals(students, response);
    }

}
