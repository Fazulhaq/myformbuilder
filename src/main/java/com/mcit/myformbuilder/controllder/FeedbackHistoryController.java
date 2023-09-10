package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.FeedbackHistory;
import com.mcit.myformbuilder.service.FeedbackHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@Tag(name = "Feedback APIs", description = "These endpoints are used for managing and manipulating queries related to the feedback part of the project")
@RequestMapping("/feedback")
public class FeedbackHistoryController {

    FeedbackHistoryService feedbackHistoryService;

    @Operation(
            summary = "Get Feedback by Id",
            description = "You can pass the feedback id and it returns the feedback if exists")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "404", description = "Feedback id not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
                @ApiResponse(responseCode = "200", description = "Successful retrieval of feedback", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FeedbackHistory.class))))
    })
    @GetMapping(value = "/{feedbackid}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackHistory> getFeeback(@PathVariable Long feedbackid){
        return new ResponseEntity<>(feedbackHistoryService.getFeedback(feedbackid), HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Feedback List",
            description = "You can call this endpoint and get the complete list of feedbacks")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "404", description = "Feedbacks not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
                @ApiResponse(responseCode = "200", description = "Successful retrieval of feedback list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FeedbackHistory.class))))
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedbackHistory>> getFeedbacks(){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacks(), HttpStatus.OK);
    }

    @Operation(
            summary = "Save new Feedback",
            description = "You can pass AdminId and FormId with new feedback in JSON format and it saves the feedback accordingly")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Feedback successfully added"),
                @ApiResponse(responseCode = "400", description = "Unsuccessful operation on adding feedback", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PostMapping("/user/{userid}/filledform/{formid}")
    public ResponseEntity<HttpStatus> saveFeedback(@Valid @RequestBody FeedbackHistory feedback, @PathVariable Long userid, @PathVariable Long formid){
        feedbackHistoryService.saveFeedback(feedback, userid, formid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update the Feedback",
            description = "You can pass the FeedbackId and edited feedback data in JSON format and it updates it accordingly")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Feedback successfully updated"),
                @ApiResponse(responseCode = "400", description = "Unsuccessful operation on updating feedback", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PutMapping(value = "/{feedbackid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackHistory> updateFeedback(@Valid @RequestBody FeedbackHistory feedback, @PathVariable Long feedbackid){
        return new ResponseEntity<>(feedbackHistoryService.updateFeedback(feedback, feedbackid), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Feedback by Id",
            description = "You can pass the FeedbackId and it will remove it from database")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Feedback successfully deleted"),
                @ApiResponse(responseCode = "404", description = "Feedback not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @DeleteMapping("/{feedbackid}")
    public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable Long feedbackid){
        feedbackHistoryService.deleteFeedback(feedbackid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Get related feedbacks of a Form",
            description = "Pass the filled formId and it will return all related feedbacks of that form")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successful retrieval of feedback data"),
                @ApiResponse(responseCode = "404", description = "There are no feedbacks in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @GetMapping(value = "/filledform/{formid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FeedbackHistory>> getFilledFormFeedbacks(@PathVariable Long formid){
        return new ResponseEntity<>(feedbackHistoryService.getFormFeedbacks(formid), HttpStatus.OK);
    }
    @Operation(
            summary = "Get list of feedbacks for specific form and user",
            description = "Pass the FormId and UserId and it returns the related feedbacks")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successful retrieval of feedback data"),
                @ApiResponse(responseCode = "404", description = "There are no feedback in th list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @GetMapping(value = "/form/{formid}/user/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FeedbackHistory>> getFeedbacksByFormAndUser(@PathVariable Long formid, @PathVariable Long userid){
        return new ResponseEntity<>(feedbackHistoryService.getFeedbacksByFormAndUser(formid, userid), HttpStatus.OK);
    }
}
