package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.Constants;
import com.mcit.myformbuilder.entity.FilledForm;
import com.mcit.myformbuilder.service.FilledFormService;
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
@RequestMapping("/filledform")
public class FilledFormController {

    FilledFormService filledFormService;

    @GetMapping("/{filledformid}")
    public ResponseEntity<FilledForm> getFilledForm(@PathVariable Long filledformid){
        if (filledFormService.ifFounded(filledformid) == Constants.Not_Found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(filledFormService.getFilledForm(filledformid), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FilledForm>> getFilledForms(){
        return new ResponseEntity<>(filledFormService.getFilledForms(), HttpStatus.OK);
    }

    @PostMapping("/userdata/{userid}/emptyform/{emptyformid}")
    public ResponseEntity<FilledForm> saveFilledForm(@Valid @RequestBody FilledForm filledForm, BindingResult result, @PathVariable Long userid, @PathVariable Long emptyformid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(filledFormService.saveFilledForm(filledForm, userid, emptyformid), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{filledformid}")
    public ResponseEntity<FilledForm> updateFilledForm(@Valid @RequestBody FilledForm filledForm, BindingResult result, @PathVariable Long filledformid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(filledFormService.updateFilledForm(filledForm, filledformid), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{filledformid}")
    public ResponseEntity<HttpStatus> deleteFilledForm(@PathVariable Long filledformid){
        filledFormService.deleteFilledForm(filledformid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userdata/{userid}")
    public ResponseEntity<Set<FilledForm>> getUserFilledForms(@PathVariable Long userid){
        return new ResponseEntity<>(filledFormService.getUserFilledForms(userid), HttpStatus.OK);
    }

    @GetMapping("/emptyform/{emptyformid}")
    public ResponseEntity<Set<FilledForm>> getEmptyFormUsedFilledForms(@PathVariable Long emptyformid){
        return new ResponseEntity<>(filledFormService.getEmptyFormUsedFilledForms(emptyformid), HttpStatus.OK);
    }
}
