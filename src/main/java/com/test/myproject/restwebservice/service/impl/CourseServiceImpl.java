package com.test.myproject.restwebservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.restwebservice.dao.CourseRepository;
import com.test.myproject.restwebservice.mapper.CourseMapper;
import com.test.myproject.restwebservice.model.Course;
import com.test.myproject.restwebservice.model.Review;
import com.test.myproject.restwebservice.service.CourseService;
import com.test.myproject.restwebservice.utility.Actions;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseMapper courseMapper;

	@Override
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(long id) {
		Course course = courseRepository.findById(id).get();
		List<Review> review = course.getReviews();
		return course;
	}

	@Override
	public Course createCourse(Course courseRequest) {
		for (Review review : courseRequest.getReviews()) {
			// we have to set each child with parent
			review.setCourse(courseRequest);
		}
		return courseRepository.save(courseRequest);
	}

	@Override
	@Transactional
	public Course updateCourse(Course courseRequest) {
		Course courseFromDb = null;
		Optional<Course> course0 = courseRepository.findById(courseRequest.getId());
		if (course0.isPresent())
			courseFromDb = course0.get();
		Course savedCourse = updateCourseWithAction(courseRequest, courseFromDb);
		return savedCourse;
	}

	private Course updateCourseWithAction(Course courseRequest, Course courseFromDb) {

		updateCourseField(courseRequest, courseFromDb);

		updateCourseReview(courseRequest, courseFromDb);

		return courseRepository.findById(courseRequest.getId()).get();

	}

	private void updateCourseField(Course courseRequest, Course courseFromDb) {
		if (courseRequest.getName() != null)
			courseFromDb.setName(courseRequest.getName());
	}

	private void updateCourseReview(Course courseRequest, Course courseFromDb) {
		for (Review reviewRequest : courseRequest.getReviews()) {
			if (Actions.NEW.equals(reviewRequest.getAction())) {
				reviewRequest.setCourse(courseFromDb);
				courseFromDb.getReviews().add(reviewRequest);
			} else if (Actions.UPDATE.equals(reviewRequest.getAction())) {
				for (Review reviewFromDb : courseFromDb.getReviews()) {
					long idFromDb = reviewFromDb.getId();
					long idFromRequest = reviewRequest.getId();
					if (idFromDb == idFromRequest) {
						reviewFromDb.setDescription(reviewRequest.getDescription());
						break;
					}
				}
			} else if (Actions.DELETE.equals(reviewRequest.getAction())) {
				for (Review reviewFromDb : courseFromDb.getReviews()) {
					if (reviewFromDb.getId() != null && reviewRequest.getId() != null) {

						long idFromDb = reviewFromDb.getId();
						long idFromRequest = reviewRequest.getId();
						if (idFromDb == idFromRequest) {
							courseFromDb.removeReview(reviewFromDb);
							break;
						}
					}
				}

			}
		}
	}

	@Override
	public List<Course> getCourseByReview(String review) {
		// TODO Auto-generated method stub
		List<Course> course = courseRepository.findCourseByReview(review);
		return course;
	}

}
