package com.pack.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pack.demo.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	@Query("{emailId: '?0'}")
	Optional<User> findByEmailId(String emailId);
}
