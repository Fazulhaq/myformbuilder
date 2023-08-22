package com.mcit.myformbuilder.exception;

public class EmptyFormNotFoundException extends RuntimeException{
    public EmptyFormNotFoundException(Long emptyFormId){
        super("The emptyForm id '" + emptyFormId + "' does not exist in our records");
    }
}
