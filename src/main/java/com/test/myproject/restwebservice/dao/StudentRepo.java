package com.test.myproject.restwebservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.myproject.restwebservice.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	@Query("select s from Student s")
	public List<Student> findAllStudent();

	@Query("select s from Student s where s.id = :id")
	public Student findStudentById(@Param("id") Long id);

}