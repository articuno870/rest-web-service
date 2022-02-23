package com.test.myproject.restwebservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.dao.StudentRepository;
import com.test.myproject.restwebservice.model.Student;
import com.test.myproject.restwebservice.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class StudentController {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentService studentService;

	@GetMapping("/student")
	public void check() {
		studentRepository.someOperationToUnderstandPersistenceContext();
	}

	/**
	 * To save student http://localhost:8081/students { "name": "aaa",
	 * "passport":{"number": "12345" } }
	 * 
	 * @param student
	 * @return
	 */
	@Operation(summary = "Save single student")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "save the student"),
			@ApiResponse(responseCode = "404", description = "bad request"), })
	@PostMapping("/students")
	public Student saveStudents(@RequestBody Student student) {
		logger.info("-> Inside saveStudents() {}", student.toString());
		return studentService.saveStudents(student);
	}

	/**
	 * http://localhost:8081/students/update
	 * 
	 * To delete passport json Request body: { "id": 20001, "name": "rrrrr",
	 * "passport": { "action": "DELETE", "number": "PP111", "id": 40001 } }
	 * 
	 * To update: { "id": 20001, "name": "rrrrr", "passport": { "action": "UPDATE",
	 * "number": "PP111", "id": 40001 } }
	 * 
	 * @param student
	 * @return
	 */
	@PostMapping("/students/update")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}

	@GetMapping(path = "/students", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable("id") long id) {
		logger.info("just checking");
		return studentService.getStudentById(id);
	}
}
