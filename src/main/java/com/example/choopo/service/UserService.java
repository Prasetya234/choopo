package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    // GET ALL USER
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<User> users = new ArrayList<>();

            users = userRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("status","SUCCESS");
            response.put("message","SUCCESS");
            response.put("content", users);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST USER
    public User createUser(@Valid @RequestBody User userRequest){
        return userRepository.save(userRequest);
    }

    // GET USER BY ID
    public ResponseEntity<User> getUserById(@PathVariable(value = "id")Long user_id) throws ResourceNotFoundExceotion {
        Optional<User> user = Optional.ofNullable(userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", user);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("USER ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE USER BY ID
    public ResponseEntity<User> updateUserById(@PathVariable (value = "id")Long user_id,User userDetails)
            throws ResourceNotFoundExceotion{
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOTFOUND"));

        user.setUserType(userDetails.getUserType());
        user.setUserName(userDetails.getUserName());
        user.setUserCode(userDetails.getUserCode());
        user.setPassword(userDetails.getPassword());
        user.setUserStatus(user.getUserStatus());
        final User updateUserById = userRepository.save(user);
        return ResponseEntity.ok(updateUserById);
    }

    // DELETE USER BY ID
    public Map<String, Boolean> deleteUserById(@PathVariable (value = "id")Long user_id)
            throws ResourceNotFoundExceotion {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOTFOUND"));

        userRepository.delete(user);
        Map<String , Boolean> resonse = new HashMap<>();
        resonse.put("DELETED", Boolean.TRUE);
        return resonse;
    }
}
