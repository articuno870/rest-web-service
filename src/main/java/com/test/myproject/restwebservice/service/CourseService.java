package com.test.myproject.restwebservice.service;

import java.util.List;

import com.test.myproject.restwebservice.model.Course;

public interface CourseService {

	List<Course> getAllCourse();

	Course getCourseById(long id);

	Course createCourse(Course course);

	Course updateCourse(Course course);

	
}
