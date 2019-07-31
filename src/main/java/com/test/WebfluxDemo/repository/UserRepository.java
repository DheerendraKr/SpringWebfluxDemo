package com.test.WebfluxDemo.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.test.WebfluxDemo.model.User;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
	
	@Query("{'userName':?0}")
	Mono<User> findByuserName(String userName);
}
