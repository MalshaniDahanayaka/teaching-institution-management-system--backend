package com.isa.teachingInstitution.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table(name = "course", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"course_id", "teacher_username"})
})
public class Course {

    @Id
    @Column(name = "course_id", nullable = false)
    private String courseID;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "about_course")
    private String aboutCourse;

    @Column(name = "time_slot")
    private String timeSlot;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_username", referencedColumnName = "username")
    private Teacher teacher;

    public Course(String courseID, String courseName, String aboutCourse, String timeSlot, Teacher teacher) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.aboutCourse = aboutCourse;
        this.timeSlot = timeSlot;
        this.teacher = teacher;
    }

}