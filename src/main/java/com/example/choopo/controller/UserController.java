package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return userService.getAll();
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value = "id")Long user_id) throws ResourceNotFoundExceotion{
       return userService.getUserById(user_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable (value = "id")Long user_id,@Valid @RequestBody User userDetails) throws ResourceNotFoundExceotion{
        return userService.updateUserById(user_id, userDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable (value = "id")Long user_id) throws ResourceNotFoundExceotion {
        return userService.deleteUserById(user_id);
    }
}
