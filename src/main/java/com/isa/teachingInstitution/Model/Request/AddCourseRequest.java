package com.isa.teachingInstitution.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCourseRequest {

    private String courseID;
    private String courseName;
    private String aboutCourse;
    private String timeSlot;
    private String teacherUserName;
}
