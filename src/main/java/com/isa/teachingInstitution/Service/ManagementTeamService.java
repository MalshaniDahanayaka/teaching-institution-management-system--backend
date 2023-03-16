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
import java.util.stream.Collectors;

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
            return studentRepository.findAll();

        } catch (Exception e) {
            throw new ManagementTeamServiceException("Error retrieving students", e);
        }
    }

    public List<Teacher> getAllTeachers(){
        try {
            return  teacherRepository.findAll();
        } catch (Exception e) {
            throw new ManagementTeamServiceException("Error retrieving teachers", e);
        }
    }

    public List<User> getAllUsers(){
        try{
            return userRepository.findAll();

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
                addCourseRequest.getTimeSlot(),
                null
        );

        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    public List<Teacher> getTeachersWithNoCourses() {
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Teacher> teachersWithCourses = courseRepository.findAll().stream()
                .map(course -> course.getTeacher())
                .filter(teacher -> teacher != null)
                .collect(Collectors.toList());
        List<Teacher> teachersWithNoCourses = new ArrayList<>(allTeachers);
        teachersWithNoCourses.removeAll(teachersWithCourses);
        return teachersWithNoCourses;
    }
    public List<Student> courseEnrolledStudents(String courseID){
        Course course = courseRepository.findById(courseID).get();
        List<Student> students = course.getStudents();
        List<Student> studentList = new ArrayList<>();
        for(Student student:students){
            studentList.add(
                    new Student(
                            student.getFirstName(),
                            student.getLastName(),
                            student.getUsername(),
                            student.getEmail(),
                            student.getPassword(),
                            student.getRole(),
                            student.getStudentID()
                    )
            );
        }
        return studentList;
    }
}