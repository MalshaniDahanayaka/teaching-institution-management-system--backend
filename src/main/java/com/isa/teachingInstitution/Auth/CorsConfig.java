package com.isa.teachingInstitution.Auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
  private static final String GET = "GET";
  private static final String POST = "POST";
  private static final String PUT = "PUT";
  private static final String DELETE = "DELETE";
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedMethods(GET, PUT, DELETE, POST)
              .allowedHeaders("*")
              .allowedOrigins("http://localhost:3000", "http://localhost:8095","http://localhost:3010","http://localhost:8096")
              .allowCredentials(false);

    }
  }

