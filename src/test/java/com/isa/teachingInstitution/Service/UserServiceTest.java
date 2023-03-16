package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        // Arrange
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course("SENG-10-1", "Introduction to Computer Science",
                "This is for test","Monday",new Teacher()));
        expectedCourses.add(new Course("SENG-20-1", "Data Structures and Algorithms",
                "This is for test","Tuesday",new Teacher()));
        when(courseRepository.findAll()).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = userService.getAllCourses();

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }

    @Test
    void testCourseEnrollments() {
        // Arrange
        String courseId = "SENG-10-1";
        Course course = new Course("SENG-10-1", "Introduction to Computer Science",
                "This is for test","Monday",new Teacher());
        List<Student> students = new ArrayList<>();
        students.add(new Student("Bhagya",
                "Dahanayaka",
                "BGD",
                "bhagya@gmail.com",
                "2222",
                "Student",
                "SE-2018-001"));
        students.add(new Student("Kasun",
                "Madhumal",
                "SKasun",
                "kasun@gmail.com",
                "3333",
                "Student",
                "SE-2018-002"));
        course.setStudents(students);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // Act
        int actualEnrollments = userService.courseEnrollments(courseId);

        // Assert
        assertEquals(students.size(), actualEnrollments);
    }
}
