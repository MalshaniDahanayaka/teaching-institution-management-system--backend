package com.isa.teachingInstitution.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseEnrollRequest {

    private String username;
    private String courseID;


}
