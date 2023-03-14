package com.isa.teachingInstitution.Sample;

import com.isa.teachingInstitution.Model.Request.SignupRequest;
import com.isa.teachingInstitution.Service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class SampleData {
    @Autowired
    private SignupService signupService;

    @PostConstruct
    public void setup(){
        SignupRequest request1 = new SignupRequest(
                "Admin",
                "Admin",
                "Admin",
                "admin@gmail.com",
                "0000",
                "Admin",
                "Admin-01"
        );
        signupService.createUser(request1);

        SignupRequest request2 = new SignupRequest(
                "Nisha",
                "Karunarathna",
                "HMNPK",
                "nisha@gmail.com",
                "1111",
                "Teacher",
                "SE-TE-001"
        );
        signupService.createUser(request2);

        SignupRequest request3 = new SignupRequest(
                "Bhagya",
                "Dahanayaka",
                "BGD",
                "bhagya@gmail.com",
                "2222",
                "Student",
                "SE-2018-001"
        );
        signupService.createUser(request3);


    }

}
