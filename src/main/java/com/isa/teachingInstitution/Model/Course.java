package com.isa.teachingInstitution.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table
public class Course {

    @Id
    private String courseID;
    private String courseName;
    private String aboutCourse;
    private String teacher;
    private String timeSlot;


}