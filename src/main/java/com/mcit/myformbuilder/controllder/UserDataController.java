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

    @GetMapping("/{userid}")
    public ResponseEntity<UserData> getUserData(@PathVariable Long userid){
        return new ResponseEntity<>(userDataService.getUserData(userid), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserData>> getAllUserData(){
        return new ResponseEntity<>(userDataService.getAllUserData(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserData> saveUserData(@RequestBody UserData userData){
        return new ResponseEntity<>(userDataService.saveUserData(userData), HttpStatus.CREATED);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserData> updateUserData(@RequestBody UserData userData, @PathVariable Long userid){
        return new ResponseEntity<>(userDataService.updateUserData(userData, userid), HttpStatus.OK);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<HttpStatus> deleteUserData(@PathVariable Long userid){
        userDataService.deleteUserData(userid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
