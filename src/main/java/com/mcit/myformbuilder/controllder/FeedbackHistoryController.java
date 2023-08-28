package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.Constants;
import com.mcit.myformbuilder.entity.FeedbackHistory;
import com.mcit.myformbuilder.service.FeedbackHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@Tag(name = "Feedback APIs", description = "These endpoints are used for managing and manipulating queries related to the feedback part of the project")
@RequestMapping("/feedback")
public class FeedbackHistoryController {

    FeedbackHistoryService feedbackHistoryService;

    @Operation(summary = "Get Feedback by Id", description = "You can pass the feedback id and it returns the feedback if exists")
    @GetMapping("/{feedbackid}")
    public ResponseEntity<FeedbackHistory> getFeeback(@PathVariable Long feedbackid){
        if (feedbackHistoryService.ifFounded(feedbackid) == Constants.Not_Found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(feedbackHistoryService.getFeedback(feedbackid), HttpStatus.OK);
        }
    }

    @Operation(summary = "Get All Feedback List", description = "You can call this endpoint and get the complete list of feedbacks")
    @GetMapping("/all")
    public ResponseEntity<List<FeedbackHistory>> getFeedbacks(){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacks(), HttpStatus.OK);
    }

    @Operation(summary = "Save new Feedback", description = "You can pass AdminId and FormId with new feedback in JSON format and it saves the feedback accordingly")
    @PostMapping("/user/{userid}/filledform/{formid}")
    public ResponseEntity<FeedbackHistory> saveFeedback(@Valid @RequestBody FeedbackHistory feedback, BindingResult result, @PathVariable Long userid, @PathVariable Long formid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(feedbackHistoryService.saveFeedback(feedback, userid, formid), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Update the Feedback", description = "You can pass the FeedbackId and edited feedback data in JSON format and it updates it accordingly")
    @PutMapping("/{feedbackid}")
    public ResponseEntity<FeedbackHistory> updateFeedback(@Valid @RequestBody FeedbackHistory feedback, BindingResult result, @PathVariable Long feedbackid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(feedbackHistoryService.updateFeedback(feedback, feedbackid), HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete Feedback by Id", description = "You can pass the FeedbackId and it will remove it from database")
    @DeleteMapping("/{feedbackid}")
    public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable Long feedbackid){
        feedbackHistoryService.deleteFeedback(feedbackid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get related feedbacks of a Form", description = "Pass the filled formId and it will return all related feedbacks of that form")
    @GetMapping("/filledform/{formid}")
    public ResponseEntity<Set<FeedbackHistory>> getFilledFormFeedbacks(@PathVariable Long formid){
        return new ResponseEntity<>(feedbackHistoryService.getFormFeedbacks(formid), HttpStatus.OK);
    }

    @Operation(summary = "Get list of feedbacks for specific form and user", description = "Pass the FormId and UserId and it returns the related feedbacks")
    @GetMapping("/form/{formid}/user/{userid}")
    public ResponseEntity<Set<FeedbackHistory>> getFeedbacksByFormAndUser(@PathVariable Long formid, @PathVariable Long userid){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacksByFormAndUser(formid, userid), HttpStatus.OK);
    }
}
