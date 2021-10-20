package com.test.myproject.restwebservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.restwebservice.dao.PostRepository;
import com.test.myproject.restwebservice.dao.UserRepository;
import com.test.myproject.restwebservice.exception.UserNotFoundException;
import com.test.myproject.restwebservice.model.Post;
import com.test.myproject.restwebservice.model.User;
import com.test.myproject.restwebservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	/**
	 * Using query method
	 */
	@Override
	public List<User> getAllUserUsingQuery() {
		System.out.println("reached");
		List<User> user = userRepository.getAllUsers();
		return user;
		// return null;
	}

	/**
	 * Using jpa predifined method
	 */
	@Override
	public List<User> getAllUser() {
		System.out.println("reached");
		List<User> userDb = userRepository.findAll();
		return userDb;
	}

	@Override
	public User getUserById(int id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + id);
		}
		return user.get();
	}

	@Override
	public User saveUser(@Valid User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Post createPost(int id, Post post) throws UserNotFoundException {
		User user = getUserById(id);
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		return savedPost;
	}

}
