package com.isa.teachingInstitution.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="student_course_enrollment",
uniqueConstraints=@UniqueConstraint(columnNames={"student_id", "course_id"}))
public class StudentCourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_id")
    private String studentID;

    @Column(name = "course_id")
    private String courseID;

    public StudentCourseEnrollment(String studentID, String courseID){
        this.studentID = studentID;
        this.courseID = courseID;
    }


}
