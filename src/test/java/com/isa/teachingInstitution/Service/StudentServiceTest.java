package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Request.CourseEnrollRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.StudentCourseEnrollment;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Repository.StudentCourseEnrollmentRepository;
import com.isa.teachingInstitution.Repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentCourseEnrollmentRepository studentCourseEnrollmentRepository;

    @InjectMocks
    private StudentService studentService;

    private StudentCourseEnrollment studentCourseEnrollment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        studentCourseEnrollment = new StudentCourseEnrollment("student123", "seng001");
    }

    @Test
    public void testGetProfileData() {

        Student student = new Student(
                "john",
                "smith",
                "john90",
                "john@gmail.com",
                "password",
                "Student",
                "SE/2018/011"
        );
        when(studentRepository.findById("john")).thenReturn(Optional.of(student));

        Student result = studentService.getProfileData("john");

        Assertions.assertEquals(student, result);

    }

    @Test
    public void testEnrollToCourse() {
        CourseEnrollRequest courseEnrollRequest = new CourseEnrollRequest("john", "seng01");
        StudentCourseEnrollment enrollment = new StudentCourseEnrollment(courseEnrollRequest.getUsername()
                , courseEnrollRequest.getCourseID());
        when(studentCourseEnrollmentRepository.save(enrollment)).thenReturn(enrollment);

        StudentCourseEnrollment result = studentService.enrollToCourse(courseEnrollRequest, "john");

        Assertions.assertEquals(courseEnrollRequest.getUsername(), result.getStudentID());
        Assertions.assertEquals(courseEnrollRequest.getCourseID(), result.getCourseID());
    }

    @Test
    public void testEnrollToCourseAccessDenied() {
        CourseEnrollRequest courseEnrollRequest = new CourseEnrollRequest("jane", "1");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            studentService.enrollToCourse(courseEnrollRequest, "john");
        });
    }

    @Test
    public void testGetEnrolledCourses() {

        Course course1 = new Course("seng01", "English", "Learn English", "Monday 10:00 - 12:00", new Teacher(
                "john", "smith", "johnsmith", "john@gmail.com", "password", "Teacher", "teacher01"
        ));


        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        Student student = new Student("john",
                "smith",
                "john90",
                "john@gmail.com",
                "password",
                "Student",
                "SE/2018/011");
        student.setCourses(courses);


        when(studentRepository.findById("john")).thenReturn(Optional.of(student));

        List<Course> result = studentService.getEnrolledCourses("john");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("English", result.get(0).getCourseName());
        Assertions.assertEquals("Learn English", result.get(0).getAboutCourse());
    }


    @Test
    void checkEnrollment_ReturnsFalse_WhenStudentIsNotEnrolledInCourse() {
        when(studentCourseEnrollmentRepository.findByCourseIDAndUsername(anyString(), anyString()))
                .thenReturn(null);

        boolean result = studentService.checkEnrollment("seng001", "student123");

        assertFalse(result);
    }

    @Test
    void checkEnrollment_ReturnsTrue_WhenStudentIsEnrolledInCourse() {

        when(studentCourseEnrollmentRepository.findByCourseIDAndUsername(anyString(), anyString()))
                .thenReturn(studentCourseEnrollment);

        boolean result = studentService.checkEnrollment("seng001", "student123");

        assertTrue(result);
    }

    @Test
    void unenrollStudent_ReturnsNotFound_WhenStudentIsNotEnrolledInCourse() {
        when(studentCourseEnrollmentRepository.findByCourseIDAndUsername(anyString(), anyString()))
                .thenReturn(null);

        ResponseEntity<?> response = studentService.unenrollStudent("seng001", "student123");

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void unenrollStudent_ReturnsOk_WhenStudentIsEnrolledInCourseAndUnenrollsSuccessfully() {
        when(studentCourseEnrollmentRepository.findByCourseIDAndUsername(anyString(), anyString()))
                .thenReturn(studentCourseEnrollment);

        ResponseEntity<?> response = studentService.unenrollStudent("seng001", "student123");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}