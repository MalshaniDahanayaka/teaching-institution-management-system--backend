package com.isa.teachingInstitution.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
@PrimaryKeyJoinColumn(name="username")
public class Student extends User{

    @Column(name="student_id")
    private String studentID;

    @ManyToMany
    @JoinTable(
            name = "student_course_enrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName, String lastName, String username, String password, String email, String role, String studentID) {
        super(firstName, lastName, username, password, email, role);
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
