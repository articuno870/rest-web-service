package com.test.myproject.restwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.exception.UserNotFoundException;
import com.test.myproject.restwebservice.model.Post;
import com.test.myproject.restwebservice.model.User;
import com.test.myproject.restwebservice.service.UserService;

@RestController
public class UserJpaController {

	@Autowired
	UserService userService;

	@GetMapping("jpa/users/query")
	public List<User> getAllUserUsingQuery() {
		return userService.getAllUserUsingQuery();
	}

	@GetMapping("jpa/users")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	// no need of try catch block
	@GetMapping("jpa/users/{id}")
	public User getUserById(@PathVariable int id)  {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// either throw from here or from getUserById method it will go to controller
			// advice only.

			// this try catch is only to tell jvm that i am handling the exception.
			// if from getUserById() we throw any exception and use try catch block in this
			// method then control will first come here, from here either we can do solve
			// exception or we can throw. This is applicable for both checked and unchecked
			// exception.

			// it is mandatory to use throw and throws together in case of checked
			// exception.(When custom exception is extending Exception class)

			// but in case of runtime exception no need to use throws at the end it will go
			// to controller advice only.(when custom exception is extending RuntimeException class)

			// throw new RuntimeException();
			//re throwing the exception
			throw e;
		}
		return user;
	}

	@DeleteMapping("jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	@GetMapping("jpa/users/{id}/post")
	public ResponseEntity<List<Post>> saveUser(@PathVariable int id) throws UserNotFoundException {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user.getPosts(), HttpStatus.OK);
	}

	@PostMapping("jpa/users/{id}/post")
	public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) throws UserNotFoundException {
		Post savedPost = userService.createPost(id, post);
		if (savedPost != null)
			return new ResponseEntity<>(savedPost, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
