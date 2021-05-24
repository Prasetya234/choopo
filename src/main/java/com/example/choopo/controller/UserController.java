package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserImpl userService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value = "id")Long userId) throws ResourceNotFoundExceotion{
       return userService.getUserById(userId);
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable (value = "id")Long userId,@Valid @RequestBody User userDetails) throws ResourceNotFoundExceotion{
        return userService.updateUserById(userId, userDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable (value = "id")Long userId) throws ResourceNotFoundExceotion {
        return userService.deleteUserById(userId);
    }
}
