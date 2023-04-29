package com.abhiprojects.blog.blogappapis.services;

import com.abhiprojects.blog.blogappapis.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user, Integer userId);

    void deleteUserById(Integer userId);


}
