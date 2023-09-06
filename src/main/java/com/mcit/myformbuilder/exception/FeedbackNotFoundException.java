package com.mcit.myformbuilder.exception;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(Long formId, Long userId) {
        super("The feedback with user id: '" + userId + "' and form id '" + formId + "' does not exist in our records");
    }
}
