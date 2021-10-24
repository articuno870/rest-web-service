package com.test.myproject.restwebservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.myproject.restwebservice.dao.StudentRepo;
import com.test.myproject.restwebservice.model.Passport;
import com.test.myproject.restwebservice.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	@Mock
	StudentRepo studentRepo;

	@Test
	public void saveStudentsTest() {
		Student student = getStudent();

		Mockito.when(studentRepo.save(Mockito.any(Student.class))).thenReturn(student);
		Student response = studentServiceImpl.saveStudents(student);
		assertEquals(response.getId(), student.getId());
		assertEquals(response.getName(), student.getName());
		assertEquals(response.getPassport().getId(), student.getPassport().getId());
		assertEquals(response.getPassport().getNumber(), student.getPassport().getNumber());

	}

	private Student getStudent() {
		Passport passport = new Passport(101L, "num");
		return new Student(101L, "test", passport);
	}

}
