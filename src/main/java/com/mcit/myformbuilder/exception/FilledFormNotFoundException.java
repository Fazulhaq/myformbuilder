package com.mcit.myformbuilder.exception;

public class FilledFormNotFoundException extends RuntimeException{
    public FilledFormNotFoundException(Long FilledFormId){
        super("The form with id " + FilledFormId + " not found in database");
    }
}
