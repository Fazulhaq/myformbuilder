package com.mcit.myformbuilder.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("The user id '" + userId + "' does not exist in our records");
    }
}
