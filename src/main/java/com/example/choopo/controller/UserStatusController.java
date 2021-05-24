package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserStatus;
import com.example.choopo.service.UserStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-status")
public class UserStatusController {

    @Autowired private UserStatusImpl userStatusService;

    @GetMapping("/")
    public List<UserStatus> getAll() {
        return userStatusService.getAll();
    }

    @PostMapping("/")
    public UserStatus createUserStatus(@Valid @RequestBody UserStatus userStatusRequire){
        return userStatusService.createUserStatus(userStatusRequire);
    }

    @GetMapping("/{id}")
    public UserStatus getUserStatusById(@PathVariable (value = "id")Long userStatusId) throws ResourceNotFoundExceotion{
        return userStatusService.getUserStatusById(userStatusId);
    }

    @PutMapping("/{id}")
    public UserStatus updateUserStatusById(@PathVariable (value = "id")Long userStatusId, @Valid @RequestBody UserStatus userStatusDetails) throws ResourceNotFoundExceotion {
        return userStatusService.updateUserStatusById(userStatusId,userStatusDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserStatusById(@PathVariable (value = "id")Long userStatusId) throws ResourceNotFoundExceotion {
        return userStatusService.deleteUserStatusById(userStatusId);
    }
}