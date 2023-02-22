package com.isa.teachingInstitution.Model.Response;


import com.isa.teachingInstitution.Model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class JwtResponse {
  private String userName;
  private String userRole;
  private String jwtToken;
  private String message;

  public JwtResponse(String message){
    this.message = message;
  }

  public JwtResponse(User user, String newToken, String message) {
    this.userName = user.getUsername();
    this.userRole = user.getRole();
    this.jwtToken = newToken;
    this.message = message;
  }


}
