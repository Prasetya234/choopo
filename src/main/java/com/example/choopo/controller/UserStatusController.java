package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserStatus;
import com.example.choopo.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-status")
public class UserStatusController {

    @Autowired private UserStatusService userStatusService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return userStatusService.getAll();
    }

    @PostMapping("/")
    public UserStatus createUserStatus(@Valid @RequestBody UserStatus userStatus){
        return userStatusService.createUserStatus(userStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatus> getUserStatusById(@PathVariable (value = "id")Long user_status_id) throws ResourceNotFoundExceotion{
        return userStatusService.getUserStatusById(user_status_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserStatus> updateUserStatusById(@PathVariable (value = "id")Long user_status_id,@Valid @RequestBody UserStatus userStatusDetails) throws ResourceNotFoundExceotion {
        return userStatusService.updateUserStatusById(user_status_id,userStatusDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserStatusById(@PathVariable (value = "id")Long user_status_id) throws ResourceNotFoundExceotion {
        return userStatusService.deleteUserStatusById(user_status_id);
    }
}