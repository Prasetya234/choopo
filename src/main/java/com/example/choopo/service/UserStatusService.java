package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserStatus;
import com.example.choopo.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;

@Service
public class UserStatusService {

    @Autowired private UserStatusRepository userStatusRepository;

    // GET ALL USER STATUS
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<UserStatus> userStatuses = new ArrayList<>();

            userStatuses = userStatusRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("status","SUCCESS");
            response.put("message","SUCCESS");
            response.put("content", userStatuses);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST USER STATUS
    public UserStatus createUserStatus(@Valid @RequestBody UserStatus userStatus){
        return userStatusRepository.save(userStatus);
    }

    // GET USER STATUS BY ID
    public ResponseEntity<UserStatus> getUserStatusById(@PathVariable(value = "id")Long user_status_id) throws ResourceNotFoundExceotion {

        Optional<UserStatus> userStatus = Optional.ofNullable(userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userStatus);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("USER STATUS ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    // UDPATE USER STATUS BY ID
    public ResponseEntity<UserStatus> updateUserStatusById(@PathVariable (value = "id")Long user_status_id, UserStatus userStatusDetails)
            throws ResourceNotFoundExceotion {
        UserStatus userStatus = userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));

        userStatus.setUserStatusCode(userStatusDetails.getUserStatusCode());
        userStatus.setUserStatusName(userStatusDetails.getUserStatusName());
        final UserStatus updateUserStatusById = userStatusRepository.save(userStatus);
        return ResponseEntity.ok(updateUserStatusById);
    }

    // DELETE USER STATUS BY ID
    public Map<String, Boolean> deleteUserStatusById(@PathVariable (value = "id")Long user_status_id)
            throws ResourceNotFoundExceotion {
        UserStatus userStatus= userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));

        userStatusRepository.delete(userStatus);
        Map<java.lang.String, java.lang.Boolean> response = new HashMap<>();
        response.put("DELETED", java.lang.Boolean.TRUE);
        return response;
    }
}
