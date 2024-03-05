package com.test.myproject.restwebservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.test.myproject.restwebservice.model.Person;

@Repository
public class PersonRepository {

	@PersistenceContext
	EntityManager entityManager;

	public Person findPersonById(int id) {
		Person personFromDb = entityManager.find(Person.class, id);
		return personFromDb;
	}

	public List<Person> findAllPerson() {
		List<Person> personList = entityManager.createNamedQuery("get_all_person", Person.class).getResultList();
		return personList;
	}

	/**
	 * using named query with "sql result set mapping", but using jpql only
	 */
	public Person findAllIdAndAddress(int id) {
		TypedQuery<Person> query = entityManager.createNamedQuery("get_only_id_address", Person.class);
		query.setParameter("personId", id);
		Person person = query.getSingleResult();
		return person;
	}

	/**
	 * using sql native query
	 * @return
	 */
	public List<Person> findAllByNativeSqlQuery() {
		Query query = entityManager.createNativeQuery("Select * from Person", Person.class);
		List<Person> personList = query.getResultList();
		return personList;
	}
}
