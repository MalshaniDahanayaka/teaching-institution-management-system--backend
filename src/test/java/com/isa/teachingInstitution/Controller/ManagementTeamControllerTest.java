package com.isa.teachingInstitution.Controller;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Request.AddCourseRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Service.ManagementTeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagementTeamControllerTest {

    @Mock
    private ManagementTeamService managementTeamService;

    @InjectMocks
    private ManagementTeamController managementTeamController;


    @Test
    void test_GetAllCourses() {
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course(
                "SENG-10-1",
                "Web Application",
                "This is for SE students",
                "Monday",
                new Teacher("Kumara",
                        "Dahanayaka",
                        "DKumara",
                        "kumara@gmail.com",
                        "3333",
                        "Teacher",
                        "SE-TE-002")
        ));

        when(managementTeamService.getAllCourses()).thenReturn(expectedCourses);

        List<Course> actualCourses = managementTeamController.getAllCourses();

        assertEquals(expectedCourses, actualCourses);
    }

    @Test
    void test_GetAllStudents() {
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student(
                "Malshani",
               "Dahanayaka",
                "mmd",
                "1111",
                "mmd@gmail.com",
                "Student",
                "SE-2018-011"
        ));

        when(managementTeamService.getAllStudents()).thenReturn(expectedStudents);

        List<Student> actualStudents = managementTeamController.getAllStudents();

        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    void test_GetAllTeachers(){
        List<Teacher> expectedTeachers = new ArrayList<>();
        expectedTeachers.add(new Teacher(
                "Kumara",
                "Dahanayaka",
                "DKumara",
                "kumara@gmail.com",
                "3333",
                "Teacher",
                "SE-TE-002"
        ));

        when(managementTeamService.getAllTeachers()).thenReturn(expectedTeachers);

        List<Teacher> actualTeachers = managementTeamController.getAllTeachers();

        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    void test_GetAllUsers(){
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(
                "Kumara",
                "Dahanayaka",
                "DKumara",
                "kumara@gmail.com",
                "3333",
                "Teacher"
        ));

        when(managementTeamService.getAllUsers()).thenReturn(expectedUsers);

        List<User> actualUsers = managementTeamController.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void test_create_course(){

        AddCourseRequest addCourseRequest = new AddCourseRequest(
                "SENG-20-1",
                "Database Design",
                "This is SE students",
                "Thursday",
                "HMNPK");

        Course createdCourse = new Course();
        createdCourse.setCourseID("SENG-20-1");
        createdCourse.setCourseName("Database Design");
        createdCourse.setAboutCourse("This is SE students");
        createdCourse.setTimeSlot("Thursday");
        createdCourse.setTeacher(new Teacher("HMNPK",
                "Nisha",
                "Karunarathna",
                "nisha@gmail.com",
                "1111",
                "Teacher",
                "SE-TE-001"));

        when(managementTeamService.createCourse(addCourseRequest)).thenReturn(createdCourse);

        Course course = managementTeamController.createCourse(addCourseRequest);

        assertEquals(createdCourse,course);

    }

}