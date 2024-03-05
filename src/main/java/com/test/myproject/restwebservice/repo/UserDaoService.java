package com.test.myproject.restwebservice.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.myproject.restwebservice.model.User;

@Component
public class UserDaoService {

	private static List<User> list = new ArrayList<>();

	private static int usersCount = 3;

	static {
		list.add(new User(1, "Adam", new Date()));
		list.add(new User(2, "Eve", new Date()));
		list.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return list;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		list.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : list) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User deleteById(int id) {
		Iterator<User> iterator = list.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
		return null;
	}

}