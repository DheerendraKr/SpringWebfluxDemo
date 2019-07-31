package com.test.WebfluxDemo.controller;

import com.test.WebfluxDemo.controller.advise.response.ErrorResponse;
import com.test.WebfluxDemo.service.impl.UserServiceImpl;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.WebfluxDemo.model.User;
import com.test.WebfluxDemo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl user;

	/**
	 * Fetch all user details.
	 * @return
	 */
	@RequestMapping(value = "/user-list",produces = "application/json",method = RequestMethod.GET)
	public Flux<User> userList(){
		return user.allUsersDetails();
	}

	/**
	 * Fetch user details for provided username.
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "user/{userName}", method = RequestMethod.GET, produces = "application/json")
	public Mono<ResponseEntity<User>> getUserDetails(@PathVariable("userName") String userName){
		return user.userDetailsByUserName(userName).map(userDetails->ResponseEntity.ok(userDetails))
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	/**
	 * Add new user.
	 * @param newUser
	 * @return
	 */
	@RequestMapping(value = "/user",method = RequestMethod.POST , consumes = "application/json", produces = "application/json")
	public Mono<User> addUser(@RequestBody User newUser) {
		return user.addNewUser(newUser);
	}

	/**
	 *
	 * @param userDetails
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public Mono<ResponseEntity<User>> updateUser(@RequestBody User userDetails, @PathVariable("id")String id){
		return user.updateUser(userDetails,id).map(updatedUser-> ResponseEntity.ok(updatedUser))
		.defaultIfEmpty(ResponseEntity.badRequest().build());
	}


	/**
	 *
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "user/{userName}", method = RequestMethod.DELETE, produces = "application/json")
	public Mono<Void> deleteUser(@PathVariable("userName") String userName){
		return user.deleteUser(userName);
	}

	//User data are Sent to the client as Server Sent Events
	@RequestMapping(value = "/stream/users", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> streamAllUsers(){
		return user.allUsersDetails();
	}
}
