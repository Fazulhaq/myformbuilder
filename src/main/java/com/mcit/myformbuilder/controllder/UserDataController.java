package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/userdata")
public class UserDataController {
    UserDataService userDataService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserData> getUserData(@PathVariable Long userId){
        return new ResponseEntity<>(userDataService.getUserData(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserData>> getAllUserData(){
        return new ResponseEntity<>(userDataService.getAllUserData(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserData> saveUserData(@RequestBody UserData userData){
        return new ResponseEntity<>(userDataService.saveUserData(userData), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserData> updateUserData(@RequestBody UserData userData, @PathVariable Long userId){
        return new ResponseEntity<>(userDataService.updateUserData(userData, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUserData(@PathVariable Long userId){
        userDataService.deleteUserData(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
