package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Repository.CourseRepository;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;
    private Course course;
    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        teacher = new Teacher(
                "Nisha",
                "Karunarathna",
                "nishaK",
                "nisha@example.com",
                "1111",
                "Teacher",
                "SE-TE-001"
        );
        course = new Course(
                "SENG-10-1",
                "Intro to Computer Science",
                "An introduction to computer science",
                "Monday",
                teacher
        );
        student = new Student(
                "Bhagya",
                "Dahanayaka",
                "BGD",
                "bhagya@example.com",
                "2222",
                "Student",
                "SE-2018-001"
        );
    }

    @Test
    public void test_GetProfileData_returnsTeacher() {
        when(teacherRepository.findById("nishaK")).thenReturn(Optional.of(teacher));

        Teacher result = teacherService.getProfileData("nishaK");

        assertEquals(teacher, result);
    }

    @Test
    public void test_CourseEnrolledStudents_returnsEnrolledStudents() {
        course.setStudents(Arrays.asList(student));
        when(courseRepository.findById("SENG-10-1")).thenReturn(Optional.of(course));

        List<Student> result = teacherService.courseEnrolledStudents("SENG-10-1");

        assertEquals(Arrays.asList(student), result);
    }

//    @Test
//    public void test_TeacherCourse_returnsCourse() {
//        when(teacherRepository.findById("nishaK")).thenReturn(Optional.of(teacher));
//
//        Course result = teacherService.teacherCourse("nishaK");
//
//        assertEquals(course, result);
//    }
}
