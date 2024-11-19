package com.nikhil.blog.services;

import com.nikhil.blog.config.AppConstants;
import com.nikhil.blog.dto.UserDto;
import com.nikhil.blog.entities.Role;
import com.nikhil.blog.entities.User;
import com.nikhil.blog.exceptions.ResourceNotFoundException;
import com.nikhil.blog.repositories.RoleRepo;
import com.nikhil.blog.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto request) {
        User user = dtoToUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        return userToDto(userRepo.save(user));
    }

    @Override
    public UserDto createUser(UserDto request) {
        User user = userRepo.save(dtoToUser(request));
        return userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto request, Integer userId) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","ID",userId));

        existingUser.setUsername(request.getUsername());
        existingUser.setPassword(request.getPassword());
        existingUser.setEmail(request.getEmail());
        existingUser.setAbout(request.getAbout());

        User updatedUser = userRepo.save(existingUser);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","ID",userId));
        return userToDto(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","ID",userId));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> dtos = userRepo.findAll().stream().map(this::userToDto).toList();
        return dtos;
    }

    private User dtoToUser(UserDto dto) {
//        User user = User.builder()
//                .username(requestDto.getUsername())
//                .email(requestDto.getEmail())
//                .password(requestDto.getPassword())
//                .about(requestDto.getAbout())
//                .build();
        return modelMapper.map(dto, User.class);
    }

    private UserDto userToDto(User user) {
//        UserResponseDto responseDto = UserResponseDto.builder()
//                .userId(user.getUserId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .about(user.getAbout())
//                .build();
        return modelMapper.map(user, UserDto.class);
    }

}
