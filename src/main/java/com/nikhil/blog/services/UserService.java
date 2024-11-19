package com.nikhil.blog.services;

import com.nikhil.blog.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto request);
    UserDto createUser(UserDto request);
    UserDto updateUser(UserDto request, Integer userId);
    UserDto getUserById(Integer userId);
    void deleteUser(Integer userId);
    List<UserDto> getUsers();

}
