package com.abhiprojects.blog.blogappapis.controllers;

import com.abhiprojects.blog.blogappapis.payloads.UserDto;
import com.abhiprojects.blog.blogappapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //POST-create the new user details
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT-update the user details...
    @PutMapping("/{userId}")                                             //else @PathVariable("userId") Integer id
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
    {
        UserDto updatedUser=this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE - delete the user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId)
    {
        this.userService.deleteUserById(userId);
        return ResponseEntity.ok(Map.of("message","User deleted successfully"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto=this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> usersList=this.userService.getAllUsers();
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }

}
