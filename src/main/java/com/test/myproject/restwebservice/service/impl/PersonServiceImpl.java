package com.test.myproject.restwebservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.restwebservice.dao.PersonRepository;
import com.test.myproject.restwebservice.model.Person;
import com.test.myproject.restwebservice.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		return personRepository.findAllPerson();
	}

	@Override
	public Person getById(int id) {
		return personRepository.findPersonById(id);

	}

	@Override
	public Person findAllIdAndAddress(int id) {
		return personRepository.findAllIdAndAddress(id);

	}
	
	@Override
	public List<Person> findAllByNativeSqlQuery() {
		return personRepository.findAllByNativeSqlQuery();
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePerson(int id) {
		// TODO Auto-generated method stub

	}



}
