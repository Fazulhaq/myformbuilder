package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.Constants;
import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.service.UserDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/userdata")
public class UserDataController {
    UserDataService userDataService;

    @GetMapping("/{userid}")
    public ResponseEntity<UserData> getUserData(@PathVariable Long userid){
        if (userDataService.ifFounded(userid) == Constants.Not_Found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(userDataService.getUserData(userid), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserData>> getAllUserData(){
        return new ResponseEntity<>(userDataService.getAllUserData(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserData> saveUserData(@Valid @RequestBody UserData userData, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userDataService.saveUserData(userData), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserData> updateUserData(@Valid @RequestBody UserData userData, BindingResult result, @PathVariable Long userid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userDataService.updateUserData(userData, userid), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<HttpStatus> deleteUserData(@PathVariable Long userid){
        userDataService.deleteUserData(userid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
