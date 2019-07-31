package com.test.WebfluxDemo.controller.advise;


import com.test.WebfluxDemo.controller.advise.response.ErrorResponse;
import com.test.WebfluxDemo.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvise{

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity handleUserNotFoundException(UserNotFound exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorResponse("Username does not exist"));
    }

}
