package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Exceptions.ManagementTeamServiceException;
import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Request.AddCourseRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Repository.CourseRepository;
import com.isa.teachingInstitution.Repository.StudentRepository;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementTeamService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Course> getAllCourses(){
        try {
            return courseRepository.findAll();

        } catch (Exception e) {
            throw new ManagementTeamServiceException("Error retrieving courses", e);
        }
    }

    public List<Student> getAllStudents(){
        try {
            List<Student> students =  studentRepository.findAll();
            List<Student> studentList = new ArrayList<>();
            for(Student student: students){
                studentList.add(
                        new Student(
                                student.getFirstName(),
                                student.getLastName(),
                                student.getUsername(),
                                "",
                                student.getEmail(),
                                student.getRole(),
                                student.getStudentID()
                        )
                );
            }
            return studentList;
        } catch (Exception e) {
            throw new ManagementTeamServiceException("Error retrieving students", e);
        }
    }

    public List<Teacher> getAllTeachers(){
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            List<Teacher> teacherList = new ArrayList<>();
            for(Teacher teacher: teachers){
                teacherList.add(
                        new Teacher(
                                teacher.getFirstName(),
                                teacher.getLastName(),
                                teacher.getUsername(),
                                teacher.getEmail(),
                                "",
                                teacher.getRole(),
                                teacher.getTeacherID()
                        )
                );
            }

            return teacherList;
        } catch (Exception e) {
            throw new ManagementTeamServiceException("Error retrieving teachers", e);
        }
    }

    public List<User> getAllUsers(){
        try{
            List<User> users = userRepository.findAll();
            List<User> userList = new ArrayList<>();
            for(User user : users){
                userList.add(
                        new User(
                                user.getFirstName(),
                                user.getLastName(),
                                user.getUsername(),
                                user.getEmail(),
                                "",
                                user.getRole()
                        )
                );
            }
            return userList;
        }catch (Exception e){
            throw new ManagementTeamServiceException("Error retrieving users",e);
        }
    }

    @Transactional
    public Course createCourse(AddCourseRequest addCourseRequest){

        Teacher teacher = teacherRepository.findById(addCourseRequest.getTeacherUserName()).get();
        Course course = new Course(
                addCourseRequest.getCourseID(),
                addCourseRequest.getCourseName(),
                addCourseRequest.getAboutCourse(),
                addCourseRequest.getTimeSlot()
        );

        course.setTeacher(teacher);
        courseRepository.save(course);
//        teacher.setCourse(course);
//        teacherRepository.save(teacher);

        return courseRepository.save(course);
    }
}