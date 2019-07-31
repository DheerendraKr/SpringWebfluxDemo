package com.test.WebfluxDemo.service.impl;

import com.test.WebfluxDemo.exceptions.UserNotFound;
import com.test.WebfluxDemo.model.User;
import com.test.WebfluxDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class UserServiceImpl {

    /**
     *
     */
    @Autowired
    UserRepository user;

    /**
     *
     * @return
     */
    public Flux<User> allUsersDetails() {
        return user.findAll();
    }

    /**
     *
     * @param userName
     * @return
     */
    public Mono<User> userDetailsByUserName(String userName) {
        return user.findByuserName(userName);
    }

    /**
     *
     * @param newUser
     * @return
     */
    public Mono<User> addNewUser(User newUser){
        return user.save(newUser);
    }

    /**
     *
     * @param userDetails
     * @param id
     * @return
     */
    public Mono<User> updateUser(User userDetails,String id){
        return user.findById(id).flatMap(dbUser->{
            if(dbUser == null){
                throw new UserNotFound("user does not exist for id: "+id);
            }
            dbUser.setFirstName(userDetails.getFirstName());
            dbUser.setLastName(userDetails.getLastName());
            dbUser.setUserName(userDetails.getUserName());
            return user.save(dbUser);
        });
    }


    /**
     *
     * @param userName
     * @return
     */
    public Mono<Void> deleteUser(String userName){
        return user.findByuserName(userName).flatMap(existingUser->{
            if(existingUser == null){
                throw new UserNotFound("User does not exist with userName: "+userName);
            }
            return user.delete(existingUser);
        });
    }

}