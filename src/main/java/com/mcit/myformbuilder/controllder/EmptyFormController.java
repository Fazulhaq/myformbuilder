package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.Constants;
import com.mcit.myformbuilder.entity.EmptyForm;
import com.mcit.myformbuilder.service.EmptyFormService;
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
@Tag(name = "Empty Form APIs", description = "These endpoints are used for managing all kinds of queries related to the structured and created new forms")
@RequestMapping("/emptyform")
public class EmptyFormController {
    EmptyFormService emptyFormService;

    @Operation(summary = "Get empty form or created form by Id", description = "Pass empty form id and it will return an empty form")
    @GetMapping("/{emptyformid}")
    public ResponseEntity<EmptyForm> getEmptyForm(@PathVariable Long emptyformid){
        if (emptyFormService.ifFounded(emptyformid) == Constants.Not_Found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(emptyFormService.getEmptyForm(emptyformid), HttpStatus.OK);
        }
    }

    @Operation(summary = "Get all empty forms", description = "This endpoint returns the complete list of empty forms")
    @GetMapping("/all")
    public ResponseEntity<List<EmptyForm>> getAllEmptyForm(){
        return new ResponseEntity<>(emptyFormService.getAllEmptyForm(), HttpStatus.OK);
    }

    @Operation(summary = "Save new created empty form", description = "Pass the AdminId and new created empty form data in Json format and it will save it into database")
    @PostMapping("/userdata/{userid}")
    public ResponseEntity<EmptyForm> saveEmptyForm(@Valid @RequestBody EmptyForm emptyform, BindingResult result, @PathVariable Long userid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(emptyFormService.saveEmptyForm(emptyform, userid), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Update empty form by empty form id", description = "Pass empty form id and the edited or redesigned empty form as Json format and it will updated into database")
    @PutMapping("/{emptyformid}")
    public ResponseEntity<EmptyForm> updateEmptyForm(@Valid @RequestBody EmptyForm emptyForm, BindingResult result, @PathVariable Long emptyformid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(emptyFormService.updateEmptyForm(emptyForm, emptyformid), HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete empty form by id", description = "Pass the empty form id and it will remove it from database")
    @DeleteMapping("/{emptyformid}")
    public ResponseEntity<HttpStatus> deleteEmptyForm(@PathVariable Long emptyformid){
            emptyFormService.deleteEmptyForm(emptyformid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get related empty forms created by specific admin", description = "Pass the AdminId and it will return list of forms created by that respected admin")
    @GetMapping("/userdata/{userid}")
    public ResponseEntity<Set<EmptyForm>> getUserEmptyForms(@PathVariable Long userid){
        return new ResponseEntity<>(emptyFormService.getUserEmptyForms(userid), HttpStatus.OK);
    }
}
