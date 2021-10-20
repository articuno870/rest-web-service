package com.test.myproject.restwebservice.service;

import java.util.List;

import javax.validation.Valid;

import com.test.myproject.restwebservice.exception.UserNotFoundException;
import com.test.myproject.restwebservice.model.Post;
import com.test.myproject.restwebservice.model.User;

public interface UserService {

	public List<User> getAllUser();

	public User getUserById(int id) throws UserNotFoundException;

	public User saveUser(@Valid User user);

	public List<User> getAllUserUsingQuery();

	public void deleteUser(int id);

	public Post createPost(int id,  Post post) throws UserNotFoundException;
}
