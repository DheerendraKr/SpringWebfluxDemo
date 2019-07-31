package com.test.WebfluxDemo.exceptions;

/**
 *
 */
public class UserNotFound extends RuntimeException {

    private static final long serialVersionId = 1L;

    public UserNotFound(final String message){
        super(message);
    }

}
