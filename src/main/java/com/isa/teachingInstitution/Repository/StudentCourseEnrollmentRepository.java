package com.isa.teachingInstitution.Repository;

import com.isa.teachingInstitution.Model.StudentCourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment, Long > {

    @Query(value = "SELECT * FROM student_course_enrollment WHERE course_id = ?1 AND student_username = ?2", nativeQuery = true)
     StudentCourseEnrollment findByCourseIDAndUsername(String courseID, String username);

}
