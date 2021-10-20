package com.test.myproject.restwebservice.service;

import java.util.List;

import com.test.myproject.restwebservice.model.Student;

public interface StudentService {

	Student saveStudents(Student student);

	Student updateStudent(Student student);

	List<Student> getAllStudents();

	Student getStudentById(long id);

}
