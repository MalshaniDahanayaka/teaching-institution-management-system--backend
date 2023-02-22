package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Exceptions.UserAlreadyExistsException;
import com.isa.teachingInstitution.Model.Request.SignupRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Repository.StudentRepository;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

import java.util.Objects;
import java.util.Optional;

@Service
public class SignupService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User createUser(SignupRequest signupRequest) throws UserAlreadyExistsException{

        Optional<User> existingUser = userRepository.findById(signupRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Username is already taken, please choose another username.");
        }
        else {

            if (Objects.equals(signupRequest.getRole().toLowerCase(), "student")) {

                Student student = new Student();
                student.setFirstName(signupRequest.getFirstName());
                student.setLastName(signupRequest.getLastName());
                student.setUsername(signupRequest.getUsername());
                student.setEmail(signupRequest.getEmail());
                student.setPassword(getEncodePassword(signupRequest.getPassword()));
                student.setRole(signupRequest.getRole());
                student.setStudentID(signupRequest.getUserID());

                studentRepository.save(student);

            }

            if (Objects.equals(signupRequest.getRole().toLowerCase(), "teacher")) {

                Teacher teacher = new Teacher();
                teacher.setFirstName(signupRequest.getFirstName());
                teacher.setLastName(signupRequest.getLastName());
                teacher.setUsername(signupRequest.getUsername());
                teacher.setEmail(signupRequest.getEmail());
                teacher.setRole(signupRequest.getRole());
                teacher.setPassword(getEncodePassword(signupRequest.getPassword()));
                teacher.setTeacherID(signupRequest.getUserID());

                teacherRepository.save(teacher);

            }

            User user = new User();
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setUsername(signupRequest.getUsername());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(getEncodePassword((signupRequest.getPassword())));
            user.setRole(signupRequest.getRole());

            return user;
        }

    }
}
