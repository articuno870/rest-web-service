package com.test.myproject.restwebservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.test.myproject.restwebservice.exception.UserNotFoundException;
import com.test.myproject.restwebservice.model.User;
import com.test.myproject.restwebservice.repo.UserDaoService;

@RestController
public class UserResource {

	@Autowired
	UserDaoService userService;

	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getAllUser(@PathVariable int id) /* throws UserNotFoundException */ {
		User user = userService.findOne(id);
		//if (user == null)
			//throw new UserNotFoundException("Not my found : " + id);

		// remaing code
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User user0 = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user0);
	}
}
