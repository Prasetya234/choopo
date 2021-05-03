package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value = "id")Long user_id)
            throws ResourceNotFoundExceotion{
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOTFOUND"));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable (value = "id")Long user_id,@Valid @RequestBody User userDetails)
            throws ResourceNotFoundExceotion{
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOTFOUND"));

        user.setUser_type(userDetails.getUser_type());
        user.setUser_name(userDetails.getUser_name());
        user.setUser_code(userDetails.getUser_code());
        user.setPassword(userDetails.getPassword());
        user.setUser_status(user.getUser_status());
        final User updateUserById = userRepository.save(user);
        return ResponseEntity.ok(updateUserById);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable (value = "id")Long user_id)
            throws ResourceNotFoundExceotion {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOTFOUND"));

        userRepository.delete(user);
        Map<String , Boolean> resonse = new HashMap<>();
        resonse.put("DELETED", Boolean.TRUE);
        return resonse;
    }
}
