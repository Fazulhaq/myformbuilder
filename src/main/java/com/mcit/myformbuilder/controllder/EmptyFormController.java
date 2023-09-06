package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.EmptyForm;
import com.mcit.myformbuilder.service.EmptyFormService;
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
@Tag(name = "Empty Form APIs", description = "These endpoints are used for managing all kinds of queries related to the structured and created new forms")
@RequestMapping("/emptyform")
public class EmptyFormController {
    EmptyFormService emptyFormService;

    @Operation(summary = "Get empty form or created form by Id", description = "Pass empty form id and it will return an empty form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Empty form id not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of empty form", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmptyForm.class))))
    })
    @GetMapping(value = "/{emptyformid}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmptyForm> getEmptyForm(@PathVariable Long emptyformid){
        return new ResponseEntity<>(emptyFormService.getEmptyForm(emptyformid), HttpStatus.OK);
    }

    @Operation(summary = "Get all empty forms", description = "This endpoint returns the complete list of empty forms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Empty form not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of empty form list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmptyForm.class))))
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmptyForm>> getAllEmptyForm(){
        return new ResponseEntity<>(emptyFormService.getAllEmptyForm(), HttpStatus.OK);
    }

    @Operation(summary = "Save new created empty form", description = "Pass the AdminId and new created empty form data in Json format and it will save it into database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empty form successfully added"),
            @ApiResponse(responseCode = "400", description = "Unsuccessful operation on adding empty form", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PostMapping("/userdata/{userid}")
    public ResponseEntity<HttpStatus> saveEmptyForm(@Valid @RequestBody EmptyForm emptyform, @PathVariable Long userid){
        emptyFormService.saveEmptyForm(emptyform, userid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update empty form by empty form id", description = "Pass empty form id and the edited or redesigned empty form as Json format and it will updated into database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empty form successfully updated"),
            @ApiResponse(responseCode = "400", description = "Unsuccessful operation on updating empty form", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PutMapping(value = "/{emptyformid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmptyForm> updateEmptyForm(@Valid @RequestBody EmptyForm emptyForm, @PathVariable Long emptyformid){
        return new ResponseEntity<>(emptyFormService.updateEmptyForm(emptyForm, emptyformid), HttpStatus.OK);
    }

    @Operation(summary = "Delete empty form by id", description = "Pass the empty form id and it will remove it from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empty form successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Empty form not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @DeleteMapping("/{emptyformid}")
    public ResponseEntity<HttpStatus> deleteEmptyForm(@PathVariable Long emptyformid){
            emptyFormService.deleteEmptyForm(emptyformid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get related empty forms created by specific admin", description = "Pass the AdminId and it will return list of forms created by that respected admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of empty form list"),
            @ApiResponse(responseCode = "404", description = "Empty form not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @GetMapping(value = "/userdata/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<EmptyForm>> getUserEmptyForms(@PathVariable Long userid){
        return new ResponseEntity<>(emptyFormService.getUserEmptyForms(userid), HttpStatus.OK);
    }
}
