package com.isa.teachingInstitution.Service;
import com.isa.teachingInstitution.Auth.JwtUtil;
import com.isa.teachingInstitution.Model.Course;
import com.isa.teachingInstitution.Model.Request.CourseEnrollRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.StudentCourseEnrollment;
import com.isa.teachingInstitution.Repository.StudentCourseEnrollmentRepository;
import com.isa.teachingInstitution.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseEnrollmentRepository studentCourseEnrollmentRepository;

    public Student getProfileData(String username){

        return studentRepository.findById(username).get();
    }

    public StudentCourseEnrollment enrollToCourse(@RequestBody CourseEnrollRequest courseEnrollRequest, String username){

        if (!courseEnrollRequest.getUsername().equals(username)) {
            throw new AccessDeniedException("You are not authorized to enroll this student.");
        }
        StudentCourseEnrollment studentCourse = new StudentCourseEnrollment(courseEnrollRequest.getUsername(),
                courseEnrollRequest.getCourseID());
        return studentCourseEnrollmentRepository.save(studentCourse);
    }

    public List<Course> getEnrolledCourses(String username){

        Student student = studentRepository.findById(username).get();
        System.out.println(student.getCourses());
        List<Course> courseList = new ArrayList<>();
        for (Course data : student.getCourses()) {

            Course course = new Course(
                    data.getCourseID(),
                    data.getCourseName(),
                    data.getAboutCourse(),
                    data.getTimeSlot(),
                    data.getTeacher()
            );
            courseList.add(course);
        }
        return courseList;
   }

    public boolean checkEnrollment(String courseID, String username) {

       StudentCourseEnrollment studentCourseEnrollment =
               studentCourseEnrollmentRepository.findByCourseIDAndUsername(courseID, username);
       if(studentCourseEnrollment == null){
           return false;
       }else {
           return true;
       }
    }

    public ResponseEntity<?> unenrollStudent(String courseID, String username){

        StudentCourseEnrollment studentCourseEnrollment =
                studentCourseEnrollmentRepository.findByCourseIDAndUsername(courseID, username);
        if (studentCourseEnrollment == null) {
            return ResponseEntity.notFound().build();
        }
        studentCourseEnrollmentRepository.delete(studentCourseEnrollment);
        return ResponseEntity.ok().build();
    }
}
