package com.isa.teachingInstitution.Model;

import com.isa.teachingInstitution.Model.Request.SignupRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name="student")
@PrimaryKeyJoinColumn(name="username")
public class Student extends User{

    @Column(name="student_id")
    private String studentID;

    public Student(String firstName, String lastName, String username, String email, String password, String role,
                   String studentID) {
        super(firstName, lastName, username, email, password, role);
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
