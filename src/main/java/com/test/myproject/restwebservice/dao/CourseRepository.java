package com.test.myproject.restwebservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.myproject.restwebservice.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("Select c from Course c join c.reviews as r where r.description = :review ")
	public List<Course> findCourseByReview(String review);

}
