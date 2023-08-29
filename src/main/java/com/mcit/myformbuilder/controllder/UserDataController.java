package com.mcit.myformbuilder.controllder;

import com.mcit.myformbuilder.entity.Constants;
import com.mcit.myformbuilder.entity.EmptyForm;
import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.service.UserDataService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Users Related APIs", description = "These endpoints are used for managing different kinds of users like Admin and Forms' consumers")
@RequestMapping("/userdata")
public class UserDataController {
    UserDataService userDataService;

    @Operation(summary = "Get user data by id", description = "Pass the user id and it will return that specific user data from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "User id not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user data", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserData.class))))
    })
    @GetMapping(value = "/{userid}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserData> getUserData(@PathVariable Long userid){
        if (userDataService.ifFounded(userid) == Constants.Not_Found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(userDataService.getUserData(userid), HttpStatus.OK);
        }
    }

    @Operation(summary = "Get list of all users", description = "Call this endpoint and it will return all the existing users in the database even they are admins or consumers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "User not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of users list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserData.class))))
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserData>> getAllUserData(){
        return new ResponseEntity<>(userDataService.getAllUserData(), HttpStatus.OK);
    }

    @Operation(summary = "Save new user to the database", description = "Send the new user data in Json format to this endpoint and it will save it to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully added"),
            @ApiResponse(responseCode = "400", description = "Unsuccessful operation on adding new user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveUserData(@Valid @RequestBody UserData userData, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            userDataService.saveUserData(userData);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Update user data by id", description = "For updating a user data, send edited user data in Json format with related user id to be updated in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "400", description = "Unsuccessful operation on updating user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PutMapping(value = "/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserData> updateUserData(@Valid @RequestBody UserData userData, BindingResult result, @PathVariable Long userid){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userDataService.updateUserData(userData, userid), HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete user by id", description = "Pass the user id to this endpoint and it will remove it from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @DeleteMapping("/{userid}")
    public ResponseEntity<HttpStatus> deleteUserData(@PathVariable Long userid){
        userDataService.deleteUserData(userid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
