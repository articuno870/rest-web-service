package com.test.myproject.restwebservice.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonInclude;

// will directly map to class
@NamedQuery(name = "get_all_person", query = "select p from Person p")
//------------------------------------------------
//named query using sql result set mapping
@SqlResultSetMapping(name = "id_address_mapper", classes = {
		@ConstructorResult(targetClass = Person.class, columns = { 
				@ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "home_address", type = String.class) }) })

@NamedNativeQuery(name = "get_only_id_address", 
				query = "select p.id, p.home_address from Person p "
		                 + "where p.id = :personId", 
		        resultSetMapping = "id_address_mapper")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String homeAddress;

	public Person() {
		super();
	}

	public Person(int id, String homeAddress) {
		this.id = id;
		this.homeAddress = homeAddress;
	}

	public Person(Integer id, String name, String homeAddress) {
		super();
		this.id = id;
		this.name = name;
		this.homeAddress = homeAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", homeAddress=" + homeAddress + "]";
	}

}
