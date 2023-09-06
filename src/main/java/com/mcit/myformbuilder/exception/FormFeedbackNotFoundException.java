package com.mcit.myformbuilder.exception;

public class FormFeedbackNotFoundException extends RuntimeException{
    public FormFeedbackNotFoundException(Long formId) {
        super("The feedback with form id: '" + formId + "' does not exist in our records");
    }
}
