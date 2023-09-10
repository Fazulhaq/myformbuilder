package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.FilledForm;
import com.mcit.myformbuilder.service.FilledFormService;
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
@Tag(name = "Filled Form APIs", description = "These endpoints are used for managing and controlling filled or used forms of the project")
@RequestMapping("/filledform")
public class FilledFormController {

    FilledFormService filledFormService;

    @Operation(
            summary = "Get filled form by id",
            description = "Pass id of filled form and it will return the filled form accordingly")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "404", description = "Filled form id not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
                @ApiResponse(responseCode = "200", description = "Successful retrieval of filled form", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FilledForm.class))))
    })
    @GetMapping(value = "/{filledformid}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilledForm> getFilledForm(@PathVariable Long filledformid){
        return new ResponseEntity<>(filledFormService.getFilledForm(filledformid), HttpStatus.OK);
    }

    @Operation(
            summary = "Get list of filled forms",
            description = "Call this endpoint and it will return all filled or used forms of client users")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "404", description = "Filled form not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
                @ApiResponse(responseCode = "200", description = "Successful retrieval of filled form list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FilledForm.class))))
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilledForm>> getFilledForms(){
        return new ResponseEntity<>(filledFormService.getFilledForms(), HttpStatus.OK);
    }

    @Operation(
            summary = "Save new filled form",
            description = "Send new filled form data in Json format with client userid and empty form id, and this endpoint will save it into database")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Filled form successfully added"),
                @ApiResponse(responseCode = "400", description = "Unsuccessful operation on adding filled form", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PostMapping("/userdata/{userid}/emptyform/{emptyformid}")
    public ResponseEntity<HttpStatus> saveFilledForm(@Valid @RequestBody FilledForm filledForm, @PathVariable Long userid, @PathVariable Long emptyformid){
        filledFormService.saveFilledForm(filledForm, userid, emptyformid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update the filled form by id",
            description = "Pass the edited data of filled form in Json format and related form id to this endpoint and it will update it into database")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Filled form successfully updated"),
                @ApiResponse(responseCode = "400", description = "Unsuccessful operation on updating filled form", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PutMapping(value = "/{filledformid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilledForm> updateFilledForm(@Valid @RequestBody FilledForm filledForm, @PathVariable Long filledformid){
        return new ResponseEntity<>(filledFormService.updateFilledForm(filledForm, filledformid), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete filled form by id",
            description = "Pass the id of filled form to this endpoint and it will deleted from database")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Filled form successfully deleted"),
                @ApiResponse(responseCode = "404", description = "Filled form not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @DeleteMapping("/{filledformid}")
    public ResponseEntity<HttpStatus> deleteFilledForm(@PathVariable Long filledformid){
        filledFormService.deleteFilledForm(filledformid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Get user related filled form by user id",
            description = "Pass the client user id into this endpoint and it will return list of filled forms used by that respected consumers")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successful retrieval of filled form data"),
                @ApiResponse(responseCode = "404", description = "There are no filled form in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @GetMapping(value = "/userdata/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FilledForm>> getUserFilledForms(@PathVariable Long userid){
        return new ResponseEntity<>(filledFormService.getUserFilledForms(userid), HttpStatus.OK);
    }
    @Operation(
            summary = "Get list of filled forms for one empty form by empty form id",
            description = "Pass the id of empty form into this endpoint and it will return list of filled forms of this kind of empty form, means how many times empty form had been used or filled by clients")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successful retrieval of filled form data"),
                @ApiResponse(responseCode = "404", description = "There are no filled form in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @GetMapping(value = "/emptyform/{emptyformid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FilledForm>> getEmptyFormUsedFilledForms(@PathVariable Long emptyformid){
        return new ResponseEntity<>(filledFormService.getEmptyFormUsedFilledForms(emptyformid), HttpStatus.OK);
    }
}
