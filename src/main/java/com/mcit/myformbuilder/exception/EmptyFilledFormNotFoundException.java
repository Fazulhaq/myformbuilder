package com.mcit.myformbuilder.exception;

public class EmptyFilledFormNotFoundException extends RuntimeException{
    public EmptyFilledFormNotFoundException(Long emptyFormId) {
        super("The filled form with empty form id: '" + emptyFormId + "' does not exist in our records");
    }
}
