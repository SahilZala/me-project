package com.pack.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.demo.entity.User;
import com.pack.demo.repository.UserRepository;
import com.pack.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public User createUser(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
}
