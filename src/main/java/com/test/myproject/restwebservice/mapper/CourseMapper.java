package com.test.myproject.restwebservice.mapper;

import org.springframework.stereotype.Component;

import com.test.myproject.restwebservice.model.Course;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class CourseMapper {

	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	MapperFacade mapper;

	private CourseMapper() {
		mapperFactory.classMap(Course.class, Course.class);
		mapper = mapperFactory.getMapperFacade();
	}

	public Course mapPersonToPersonDto(Course Course) {
		return mapper.map(Course, Course.class);
	}


}
