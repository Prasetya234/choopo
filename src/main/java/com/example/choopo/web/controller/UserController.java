package com.example.choopo.web.controller;

import com.example.choopo.web.dto.UserDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.User;
import com.example.choopo.web.service.response.CommonResponse;
import com.example.choopo.web.service.response.CommonResponseGenerator;
import com.example.choopo.web.service.UserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserImpl userService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;


    @GetMapping("/")
    public CommonResponse<List<UserDTO>> getAll() {

        Stream<Object> userList = userService.getAll().stream().map(user -> modelMapper.map(user, UserDTO.class));

        return commonResponseGenerator.successResponse(userList);

    }

    @PostMapping("/")
    public CommonResponse<UserDTO> createUser(@Valid @RequestBody UserDTO userDTORequest) throws ResourceNotFoundExceotion {
        User userRequest = modelMapper.map(userDTORequest, User.class);

        User user = userService.createUser(userRequest);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return commonResponseGenerator.successResponse(userDTO);
    }

    @GetMapping("/{id}")
    public CommonResponse<UserDTO> getUserById(@PathVariable (value = "id")Long userId) throws ResourceNotFoundExceotion{
        User getUserById = userService.getUserById(userId);

        UserDTO userDTO = modelMapper.map(getUserById, UserDTO.class);

       return commonResponseGenerator.successResponse(userDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<UserDTO> updateUserById(@PathVariable (value = "id")Long userId,@Valid @RequestBody UserDTO userDTODetails) throws ResourceNotFoundExceotion{
        User  userDetails = modelMapper.map(userDTODetails, User.class);

        User updateUser = userService.updateUserById(userId,userDetails);

        UserDTO userDTO = modelMapper.map(updateUser, UserDTO.class);

        return commonResponseGenerator.successResponse(userDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable (value = "id")Long userId) throws ResourceNotFoundExceotion {
        return userService.deleteUserById(userId);
    }
}
