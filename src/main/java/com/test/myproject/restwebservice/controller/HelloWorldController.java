package com.test.myproject.restwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.model.HelloWorldBean;

@RestController
public class HelloWorldController {

	@GetMapping("/test/{name}")
	public HelloWorldBean helloWorld(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello my world, %s", name));
	}

}
