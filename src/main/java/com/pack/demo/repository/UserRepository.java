package com.pack.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pack.demo.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

}
