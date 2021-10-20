package com.test.myproject.restwebservice.service;

import java.util.List;

import com.test.myproject.restwebservice.model.Person;

public interface PersonService {

	public List<Person> findAll();

	public Person getById(int id);

	public void updatePerson(Person person);

	public void deletePerson(int id);

	Person findAllIdAndAddress(int id);

	public List<Person> findAllByNativeSqlQuery();
}
