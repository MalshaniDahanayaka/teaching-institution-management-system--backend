package com.isa.teachingInstitution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TeachingInstitutionApplicationTests.class)
public class TeachingInstitutionApplicationTests {
	@Autowired
	private ApplicationContext context;
	@Test
	void contextLoads(){
		Assertions.assertNotNull(context);
	}
}
