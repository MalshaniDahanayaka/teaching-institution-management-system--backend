package com.isa.teachingInstitution.Service;

import com.isa.teachingInstitution.Auth.JwtUtil;
import com.isa.teachingInstitution.Model.User;
import com.isa.teachingInstitution.Model.Request.JwtRequest;
import com.isa.teachingInstitution.Model.Response.JwtResponse;
import com.isa.teachingInstitution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class LoginService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
    String username = jwtRequest.getUsername();
    String password = jwtRequest.getPassword();
    String message = authenticate(username, password);

    if(!message.equals("Successful")){
      return new JwtResponse(message);
    }
    UserDetails userDetails = loadUserByUsername(username);
    String newToken = jwtUtil.generateToken(userDetails);
    User user = userRepository.findById(username).get();
    return new JwtResponse(user, newToken, message);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findById(username).get();

    if(user != null){
      return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        getAuthority(user)
      );
    } else {
      throw new UsernameNotFoundException("Username is not found with username: "+ username);
    }
  }
  private Set getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    String role = user.getRole();
    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    return authorities;
  }

  private String authenticate(String username, String password) throws Exception{
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

      return "Successful";
    } catch (BadCredentialsException e){
      return "Invalid password";
    } catch (Exception e){
      return "Username not found";
    }
  }
}
