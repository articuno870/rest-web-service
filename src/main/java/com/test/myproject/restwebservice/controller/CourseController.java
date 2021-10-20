package com.test.myproject.restwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.model.Course;
import com.test.myproject.restwebservice.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService courseService;

	@GetMapping(path = "/course")
	public List<Course> getAllCourse() {
		return courseService.getAllCourse();
	}

	@GetMapping(path = "/courseById")
	//2nd level cache
	public Course getCourseById(@RequestParam("id") long id) {
		return courseService.getCourseById(id);
	}

	@PostMapping(path = "/course")
	public Course saveCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}

	@PostMapping(path = "/course/update")
	public Course updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}
}
