package com.test.myproject.restwebservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	// orphan removal must be in owning side(owning side means where we keep foreign
	// id in this case foreign id generated in student table i.e passport id, 
	//so that's  why student is owning side. Similarly in Course Pojo class, review is
	// owning side). 
	//Now, student wants to disconnect
	// relationship with passport so as soon as the passport doesn't have parent,
	// then it will automatically will be deleted, once passport is set as
	// null in student object, same for vice - versa(if passport doesn't have parent then it get deleted)
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "PASSPORT_ID", referencedColumnName = "ID")
	private Passport passport;

	protected Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public Student(Long id, String name, Passport passport) {
		super();
		this.id = id;
		this.name = name;
		this.passport = passport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", passport=" + passport + "]";
	}

}