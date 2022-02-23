package com.test.myproject.restwebservice.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Cacheable
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Review> reviews = new CopyOnWriteArrayList<Review>();

	protected Course() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReviews(Review review) {
		review.setCourse(this);
	}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
		//if below line is not there then it will remove course also
		review.setCourse(null);
		//return this;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}
}