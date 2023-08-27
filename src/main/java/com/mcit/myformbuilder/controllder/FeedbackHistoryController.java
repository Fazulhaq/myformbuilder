package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.Constants;
import com.mcit.myformbuilder.entity.FeedbackHistory;
import com.mcit.myformbuilder.service.FeedbackHistoryService;
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
@RequestMapping("/feedback")
public class FeedbackHistoryController {

    FeedbackHistoryService feedbackHistoryService;

    @GetMapping("/{feedbackid}")
    public ResponseEntity<FeedbackHistory> getFeeback(@PathVariable Long feedbackid){
        if (feedbackHistoryService.ifFounded(feedbackid) == Constants.Not_Found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(feedbackHistoryService.getFeedback(feedbackid), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackHistory>> getFeedbacks(){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacks(), HttpStatus.OK);
    }

    @PostMapping("/user/{userid}/filledform/{formid}")
    public ResponseEntity<FeedbackHistory> saveFeedback(@Valid @RequestBody FeedbackHistory feedback, BindingResult result, @PathVariable Long userid, @PathVariable Long formid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(feedbackHistoryService.saveFeedback(feedback, userid, formid), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{feedbackid}")
    public ResponseEntity<FeedbackHistory> updateFeedback(@Valid @RequestBody FeedbackHistory feedback, BindingResult result, @PathVariable Long feedbackid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(feedbackHistoryService.updateFeedback(feedback, feedbackid), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{feedbackid}")
    public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable Long feedbackid){
        feedbackHistoryService.deleteFeedback(feedbackid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filledform/{formid}")
    public ResponseEntity<Set<FeedbackHistory>> getFilledFormFeedbacks(@PathVariable Long formid){
        return new ResponseEntity<>(feedbackHistoryService.getFormFeedbacks(formid), HttpStatus.OK);
    }

    @GetMapping("/form/{formid}/user/{userid}")
    public ResponseEntity<Set<FeedbackHistory>> getFeedbacksByFormAndUser(@PathVariable Long formid, @PathVariable Long userid){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacksByFormAndUser(formid, userid), HttpStatus.OK);
    }
}
