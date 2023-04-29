package com.abhiprojects.blog.blogappapis.services.impl;

import com.abhiprojects.blog.blogappapis.entities.User;
import com.abhiprojects.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.abhiprojects.blog.blogappapis.payloads.UserDto;
import com.abhiprojects.blog.blogappapis.repositories.UserRepo;
import com.abhiprojects.blog.blogappapis.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper; //used to map between UserDto and domain object (User).

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();

        List<UserDto> usersDto=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser=this.userRepo.save(user);

        return this.userToDto(updatedUser);
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));

        this.userRepo.delete(user);

    }

   private User dtoToUser(UserDto userDto)
   {
       User user=this.modelMapper.map(userDto,User.class);

      // User user=new User();
      // user.setId(userDto.getId());
      // user.setName(userDto.getName());
      // user.setEmail(userDto.getEmail());
      // user.setAbout(userDto.getAbout());
      // user.setPassword(userDto.getPassword());
       return user;

   }

   public UserDto userToDto(User user)
   {
       UserDto userDto=this.modelMapper.map(user,UserDto.class);
       return userDto;
   }
}
