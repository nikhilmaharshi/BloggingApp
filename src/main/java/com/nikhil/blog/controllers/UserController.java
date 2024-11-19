package com.nikhil.blog.controllers;

import com.nikhil.blog.dto.UserDto;
import com.nikhil.blog.services.UserService;
import com.nikhil.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto request){
        UserDto userDto = userService.registerNewUser(request);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    //Post request
    @PostMapping("/create")  // /users is the API endpoint for creating a new user
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto request){
        UserDto userDto = userService.createUser(request);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //Put request
    @PutMapping("/update/{userId}")  // /update/{userId} is the API endpoint for updating a user by id
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto request, @PathVariable Integer userId){
        UserDto userDto = userService.updateUser(request, userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //Get request by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //Get request all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDto = userService.getUsers();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //Delete request by id
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully!!",true),HttpStatus.OK);
    }

}
