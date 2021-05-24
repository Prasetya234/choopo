package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserImpl userService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;


    @GetMapping("/")
    public CommonResponse<List<User>> getAll() {
        List<User> userList = userService.getAll();

        return commonResponseGenerator.successResponse(userList);

    }

    @PostMapping("/")
    public CommonResponse<User> createUser(@Valid @RequestBody User userRequest){
        User createUser = userService.createUser(userRequest);

        return commonResponseGenerator.successResponse(createUser);
    }

    @GetMapping("/{id}")
    public CommonResponse<User> getUserById(@PathVariable (value = "id")Long userId) throws ResourceNotFoundExceotion{
        User getUserById = userService.getUserById(userId);

       return commonResponseGenerator.successResponse(getUserById);
    }

    @PutMapping("/{id}")
    public CommonResponse<User> updateUserById(@PathVariable (value = "id")Long userId,@Valid @RequestBody User userDetails) throws ResourceNotFoundExceotion{
        User updateUser = userService.updateUserById(userId,userDetails);

        return commonResponseGenerator.successResponse(updateUser);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable (value = "id")Long userId) throws ResourceNotFoundExceotion {
        return userService.deleteUserById(userId);
    }
}
