package com.isa.teachingInstitution.Service;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Repository.StudentRepository;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Student getProfileData(String username){

        return studentRepository.findById(username).get();
    }
}
