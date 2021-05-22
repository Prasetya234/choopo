package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    // GET ALL USER
    public ResponseEntity<Map<String, Object>> getAll() {

            List<User> users = new ArrayList<>();

            users = userRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("status","SUCCESS");
            response.put("message","SUCCESS");
            response.put("content", users);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST USER
    public User createUser(User userRequest){
        return userRepository.save(userRequest);
    }

    // GET USER BY ID
    public ResponseEntity<User> getUserById(Long user_id) throws ResourceNotFoundExceotion {
        Optional<User> user = Optional.ofNullable(userRepository.findById(user_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", user);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // UPDATE USER BY ID
    public ResponseEntity<User> updateUserById(Long user_id,User userDetails) throws ResourceNotFoundExceotion{
        User user = userRepository.findById(user_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER ID NOTFOUND"));

        user.setUserType(userDetails.getUserType());
        user.setUserName(userDetails.getUserName());
        user.setUserCode(userDetails.getUserCode());
        user.setPassword(userDetails.getPassword());
        user.setUserStatus(user.getUserStatus());
        final User updateUserById = userRepository.save(user);

        return ResponseEntity.ok(updateUserById);
    }

    // DELETE USER BY ID
    public Map<String, Boolean> deleteUserById(Long user_id) throws ResourceNotFoundExceotion {
        User user = userRepository.findById(user_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER ID NOTFOUND"));

        userRepository.delete(user);

        Map<String , Boolean> resonse = new HashMap<>();
        resonse.put("DELETED", Boolean.TRUE);

        return resonse;
    }
}
