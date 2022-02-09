package com.test.myproject.restwebservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
class RestWebServiceApplicationTests {

	@Autowired
	Environment env;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testFile() {
		System.out.println(env.getProperty("user.name"));
		System.out.println(env.getProperty("emp.name"));
		System.out.println("");
	}

}
