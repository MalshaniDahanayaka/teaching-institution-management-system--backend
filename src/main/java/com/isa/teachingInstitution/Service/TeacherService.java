package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public Teacher getProfileData(String username){

        return teacherRepository.findById(username).get();
    }
}
