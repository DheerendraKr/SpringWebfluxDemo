package com.test.WebfluxDemo.service;

import com.test.WebfluxDemo.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> allUsersDetails();

    Mono<User> userDetailsByUserName(String userName);


    
    
}
