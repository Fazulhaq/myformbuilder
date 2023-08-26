package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.FeedbackHistory;
import com.mcit.myformbuilder.service.FeedbackHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(feedbackHistoryService.getFeedback(feedbackid), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackHistory>> getFeedbacks(){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacks(), HttpStatus.OK);
    }

    @PostMapping("/user/{userid}/filledform/{formid}")
    public ResponseEntity<FeedbackHistory> saveFeedback(@RequestBody FeedbackHistory feedback, @PathVariable Long userid, @PathVariable Long formid){
        return new ResponseEntity<>(feedbackHistoryService.saveFeedback(feedback, userid, formid), HttpStatus.CREATED);
    }

    @PutMapping("/{feedbackid}")
    public ResponseEntity<FeedbackHistory> updateFeedback(@RequestBody FeedbackHistory feedback, @PathVariable Long feedbackid){
        return new ResponseEntity<>(feedbackHistoryService.updateFeedback(feedback, feedbackid), HttpStatus.OK);
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
