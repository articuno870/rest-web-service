package com.test.myproject.restwebservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestWebServiceApplication {
	private static Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		
		SpringApplication.run(RestWebServiceApplication.class, args);
		
		
		System.out.println("hi");
		logger.debug("This is a debug message");
		String name = "abhishek";
		logger.info("This is an info message {}, {}", name, "vikas");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		logger.fatal("This is a fatal message");
	}
}
