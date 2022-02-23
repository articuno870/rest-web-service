package com.test.myproject.restwebservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.myproject.restwebservice.dao.PostRepository;
import com.test.myproject.restwebservice.dao.UserRepository;
import com.test.myproject.restwebservice.exception.UserNotFoundException;
import com.test.myproject.restwebservice.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	UserServiceImpl userServiceImpl;

	UserRepository userRepository;

	PostRepository postRepository;

	@Before
	public void setup() {
		userServiceImpl = new UserServiceImpl();

		userRepository = Mockito.mock(UserRepository.class);
		userServiceImpl.setUserRepository(userRepository);

		postRepository = Mockito.mock(PostRepository.class);
		userServiceImpl.setPostRepository(postRepository);
	}

	@Test
	public void deleteUserTest() {
		Mockito.doNothing().when(userRepository).deleteById(Mockito.anyInt());
		userServiceImpl.deleteUser(101);
		Mockito.verify(userRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
	}

	@Test
	public void getAllUserUsingQuery() {
		Mockito.when(userRepository.getAllUsers()).thenReturn(userList());
		List<User> response = userServiceImpl.getAllUserUsingQuery();
		assertEquals(101, response.get(0).getId());
		assertEquals(102, response.get(1).getId());

	}

	private List<User> userList() {
		List<User> users = new ArrayList<>();
		users.add(new User(101, "aaa", new Date()));
		users.add(new User(102, "bbb", new Date()));
		return users;
	}

	// this test case is for negative scenario
	// mockito throw only run time exception
	// make UserNotfoundException extends runtime exception then run this method
	@Test(expected = UserNotFoundException.class)
	public void getUserById() throws UserNotFoundException {
		Mockito.doThrow(new UserNotFoundException("abc")).when(userRepository).findById(Mockito.anyInt());
		userServiceImpl.getUserById(101);
	}
	


}
