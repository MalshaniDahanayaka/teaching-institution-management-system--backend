package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Exceptions.UserAlreadyExistsException;
import com.isa.teachingInstitution.Model.Request.SignupRequest;
import com.isa.teachingInstitution.Model.Student;
import com.isa.teachingInstitution.Model.Teacher;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Repository.StudentRepository;
import com.isa.teachingInstitution.Repository.TeacherRepository;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class SignupServiceTest{

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignupService signupService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_CreateUserStudent() throws UserAlreadyExistsException {
        SignupRequest request = new SignupRequest(
                "Malshani",
                "Dahanayaka",
                "mmd",
                "mmd@gmail.com",
                "1111",
                "Student",
                "SE-2018-011"
        );

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setUsername(request.getUsername());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setRole(request.getRole());
        student.setStudentID(request.getUserID());

        Mockito.when(userRepository.findById("mmd")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("1111")).thenReturn("encoded_password");
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        User user = signupService.createUser(request);

        Assertions.assertEquals("Malshani", user.getFirstName());
        Assertions.assertEquals("Dahanayaka", user.getLastName());
        Assertions.assertEquals("mmd", user.getUsername());
        Assertions.assertEquals("mmd@gmail.com", user.getEmail());
        Assertions.assertEquals("encoded_password", user.getPassword());
        Assertions.assertEquals("Student", user.getRole());

        Mockito.verify(userRepository, Mockito.times(1)).findById("mmd");
        Mockito.verify(passwordEncoder, Mockito.times(3)).encode("1111");
        Mockito.verify(studentRepository, Mockito.times(1)).save(Mockito.any(Student.class));
    }


    @Test
    public void test_CreateUserTeacher() throws UserAlreadyExistsException {

        SignupRequest request = new SignupRequest(
                "Nisha",
                "Karunarathna",
                "HMNPK",
                "nisha@gmail.com",
                "1234",
                "Teacher",
                "SE-TE-001"
        );

        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setUsername(request.getUsername());
        teacher.setEmail(request.getEmail());
        teacher.setPassword(passwordEncoder.encode(request.getPassword()));
        teacher.setRole("Teacher");
        teacher.setTeacherID("SE-TE-001");

        Mockito.when(userRepository.findById("HMNPK")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("1234")).thenReturn("encoded_password");
        Mockito.when(teacherRepository.save(teacher)).thenReturn(teacher);

        User user = signupService.createUser(request);

        Assertions.assertEquals("Nisha", user.getFirstName());
        Assertions.assertEquals("Karunarathna", user.getLastName());
        Assertions.assertEquals("HMNPK", user.getUsername());
        Assertions.assertEquals("nisha@gmail.com", user.getEmail());
        Assertions.assertEquals("encoded_password", user.getPassword());
        Assertions.assertEquals("Teacher", user.getRole());

        Mockito.verify(userRepository, Mockito.times(1)).findById("HMNPK");
        Mockito.verify(passwordEncoder, Mockito.times(3)).encode("1234");
        Mockito.verify(teacherRepository, Mockito.times(1)).save(Mockito.any(Teacher.class));


    }




}