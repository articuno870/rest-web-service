package com.test.myproject.restwebservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.myproject.restwebservice.dao.StudentRepo;
import com.test.myproject.restwebservice.model.Passport;
import com.test.myproject.restwebservice.model.Student;
import com.test.myproject.restwebservice.service.StudentService;
import com.test.myproject.restwebservice.utility.Actions;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo studentRepo;

	@Override
	public Student saveStudents(Student student) {
		// in one to one mapping, its optional to set parent in child
		// so below line is optional i.e setting student in passport
		// student.getPassport().setStudent(student);
		Student savedStudent = studentRepo.save(student);
		return savedStudent;
	}

	@Override
	public Student updateStudent(Student studentRequest) {
		Student studentFromDb = studentRepo.findStudentById(studentRequest.getId());

		// update student
		updateStudentField(studentRequest, studentFromDb);
		if (studentFromDb.getPassport() == null && Actions.NEW.equals(studentRequest.getPassport().getAction())) {
			addPassport(studentRequest, studentFromDb);
		} else if (Actions.UPDATE.equals(studentRequest.getPassport().getAction())) {
			updatePassport(studentRequest, studentFromDb);
		} else if (Actions.DELETE.equals(studentRequest.getPassport().getAction())) {
			deletePassport(studentRequest, studentFromDb);
		}
		studentRepo.save(studentFromDb);
		return null;
	}

	private void addPassport(Student studentRequest, Student studentFromDb) {
		studentFromDb.setPassport(studentRequest.getPassport());
	}

	// delete passport, in this case passport will automatically will get deleted
	// from table(as orphan removal is set in student class)
	private void deletePassport(Student studentRequest, Student studentFromDb) {
		studentFromDb.setPassport(null);
		//studentFromDb.getPassport().setStudent(null);
	}

	// update student
	private void updateStudentField(Student studentRequest, Student studentFromDb) {
		if (studentRequest.getName() != null)
			studentFromDb.setName(studentRequest.getName());
	}

	// update student passport
	private void updatePassport(Student student, Student studentFromDb) {
		Passport passportFromDB = studentFromDb.getPassport();
		passportFromDB.setNumber(student.getPassport().getNumber());
	}

	@Override
	public List<Student> getAllStudents() {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		List<Student> studentFromDb = studentRepo.findAll(sort);
		return studentFromDb;
	}

	@Override
	public Student getStudentById(long id) {
		// below line will not work for first level cache
		Student student = studentRepo.findStudentById(id);
		// below line will work for first level cache
		// Student student = studentRepo.findById(id).get();
		Student student1 = studentRepo.findById(id).get();
		return student;
	}

}
