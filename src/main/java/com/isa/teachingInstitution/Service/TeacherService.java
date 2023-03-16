package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Repository.CourseRepository;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    public Teacher getProfileData(String username){

        return teacherRepository.findById(username).get();
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

    public Course teacherCourse(String userName){
        Teacher teacher = teacherRepository.findById(userName).get();
        return new Course(
                teacher.getCourse().getCourseID(),
                teacher.getCourse().getCourseName(),
                teacher.getCourse().getAboutCourse(),
                teacher.getCourse().getTimeSlot(),
                teacher
        );
    }
}
