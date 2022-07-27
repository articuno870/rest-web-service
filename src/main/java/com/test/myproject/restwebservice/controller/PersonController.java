package com.test.myproject.restwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.model.Person;
import com.test.myproject.restwebservice.service.PersonService;

/**
 * This flow is to understand sql native query
 * @author abhis
 *
 */
@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/person")
	public Person getPersonById(@RequestParam(name = "id") int id) {
		return personService.getById(id);
	}
	
	/**
	 * dummy just to test aop
	 * @param id
	 * @return
	 */
	@GetMapping("/personWithName")
	public String getPersonById1(@RequestParam(name = "id") int id,@RequestParam String name) {
		return id+name;
	}

	@GetMapping("/persons")
	public List<Person> getAllPerson() {
		return personService.findAll();
	}
	
	@GetMapping("/persons/{id}")
	public Person findAllIdAndAddress(@PathVariable("id") int id) {
		return personService.findAllIdAndAddress(id);
	}
	@GetMapping("/persons/native")
	public List<Person> findAllByNativeSqlQuery() {
		return personService.findAllByNativeSqlQuery();
	}
	
}
