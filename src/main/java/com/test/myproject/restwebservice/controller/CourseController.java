package com.test.myproject.restwebservice.controller;

import com.test.myproject.restwebservice.model.Course;
import com.test.myproject.restwebservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(path = "/course")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping(path = "/courseById")
    // 2nd level cache
    public Course getCourseById(@RequestParam("id") long id) {
		return courseService.getCourseById(id);
    }

    @GetMapping(path = "/courseByReview/{review}")
    // 2nd level cache
    public List<Course> getCourseByReview(@PathVariable("review") String review) {
        return courseService.getCourseByReview(review);
    }

    /**
     * To create course: http://localhost:8081/course
     * <p>
     * { "name": "aaa", "reviews": [ { "description": "Best till date", "rating":
     * "FIVE" }, { "description": "easy explanation", "rating": "FIVE" } ] }
     *
     * @param course
     * @return
     */
    @PostMapping(path = "/course")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    /**
     * Use this link to format: json: http://jsonviewer.stack.hu/
     * <p>
     * To update : http://localhost:8081/course/update
     * <p>
     * all action at once
     * <p>
     * { "id": 10001, "name": "JPA in 2 Steps", "reviews": [ { "description": "bbb
     * Course", "action": "NEW" }, { "id": 50001, "description": "bad Course",
     * "action": "UPDATE" }, { "id": 50002, "description": "uuuu Course", "action":
     * "DELETE" } ] }
     *
     * @param course
     * @return
     */
    @PostMapping(path = "/course/update")
    public Course updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }
}
