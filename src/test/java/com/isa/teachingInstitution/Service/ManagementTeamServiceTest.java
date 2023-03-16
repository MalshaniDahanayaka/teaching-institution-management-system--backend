package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Repository.CourseRepository;
import com.isa.teachingInstitution.Repository.StudentRepository;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ManagementTeamServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ManagementTeamService managementTeamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_GetAllCourses() {
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course("SENG-10-1", "Java", "About Java", "Monday, 10:00AM - 12:00PM", null));
        expectedCourses.add(new Course("SENG-20-1", "Python", "About Python", "Tuesday, 2:00PM - 4:00PM", null));

        when(courseRepository.findAll()).thenReturn(expectedCourses);

        List<Course> actualCourses = managementTeamService.getAllCourses();

        assertEquals(expectedCourses, actualCourses);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllStudents() {
        // create a list of students to be returned by the mocked student repository
        List<Student> students = new ArrayList<>();
        students.add(new Student("Bhagya",
                "Dahanayaka",
                "BGD",
                "2222",
                "bhagya@example.com",
                "Student",
                "SE-2018-001"));
        students.add(new Student("Kasun",
                "Madhumal",
                "SKasun",
                "3333",
                "kasun@example.com",
                "Student",
                "SE-2018-001"));

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> returnedStudents = managementTeamService.getAllStudents();

        assertEquals(students, returnedStudents);
    }

}