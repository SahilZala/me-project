package com.pack.demo.service;

import java.util.List;

import com.pack.demo.entity.User;

public interface UserService{
	public User createUser(User user);
	public List<User> getAllUser(); 
}
