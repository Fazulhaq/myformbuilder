package com.mcit.myformbuilder.exception;

public class FeedbackHistoryNotFoundException extends RuntimeException{
    public FeedbackHistoryNotFoundException(Long FeedbackId){
        super("The feedback with id " + FeedbackId + " does not exist in our database");
    }
}
