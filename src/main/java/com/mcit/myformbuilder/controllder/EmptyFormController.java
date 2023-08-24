package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.EmptyForm;
import com.mcit.myformbuilder.service.EmptyFormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/emptyform")
public class EmptyFormController {
    EmptyFormService emptyFormService;

    @GetMapping("/{emptyformid}")
    public ResponseEntity<EmptyForm> getEmptyForm(@PathVariable Long emptyformid){
        return new ResponseEntity<>(emptyFormService.getEmptyForm(emptyformid), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmptyForm>> getAllEmptyForm(){
        return new ResponseEntity<>(emptyFormService.getAllEmptyForm(), HttpStatus.OK);
    }

    @PostMapping("/userdata/{userid}")
    public ResponseEntity<EmptyForm> saveEmptyForm(@RequestBody EmptyForm emptyform, @PathVariable Long userid){
        return new ResponseEntity<>(emptyFormService.saveEmptyForm(emptyform, userid), HttpStatus.CREATED);
    }

    @PutMapping("/{emptyformid}")
    public ResponseEntity<EmptyForm> updateEmptyForm(@RequestBody EmptyForm emptyForm, @PathVariable Long emptyformid){
        return new ResponseEntity<>(emptyFormService.updateEmptyForm(emptyForm, emptyformid), HttpStatus.OK);
    }

    @DeleteMapping("/{emptyformid}")
    public ResponseEntity<HttpStatus> deleteEmptyForm(@PathVariable Long emptyformid){
        emptyFormService.deleteEmptyForm(emptyformid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userdata/{userid}")
    public ResponseEntity<Set<EmptyForm>> getUserEmptyForms(@PathVariable Long userid){
        return new ResponseEntity<>(emptyFormService.getUserEmptyForms(userid), HttpStatus.OK);
    }
}
