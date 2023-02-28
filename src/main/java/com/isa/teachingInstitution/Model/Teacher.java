package com.isa.teachingInstitution.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name="teacher")
@PrimaryKeyJoinColumn(name="username")
public class Teacher extends User{

    @Column(name="teacher_id")
    private String teacherID;

    @OneToOne(mappedBy = "teacher")
    @JsonIgnore
    private Course course;

    public Teacher(String firstName, String lastName, String username, String email, String password, String role,
                   String teacherID) {
        super(firstName, lastName, username, email, password, role);
        this.teacherID = teacherID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
