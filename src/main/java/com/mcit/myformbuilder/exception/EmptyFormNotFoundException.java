package com.mcit.myformbuilder.exception;

public class EmptyFormNotFoundException extends RuntimeException{
    public EmptyFormNotFoundException(Long userId) {
        super("The empty form with user id: '" + userId + "' does not exist in our records");
    }
}
