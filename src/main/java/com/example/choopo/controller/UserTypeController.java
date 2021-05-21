package com.example.choopo.controller;

import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import com.example.choopo.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.exception.ResourceNotFoundExceotion;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-type")
public class UserTypeController {
    @Autowired private UserTypeRepository userTypeRepository;

    @Autowired private UserTypeService userTypeService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return userTypeService.getAll();
    }

    @PostMapping("/")
    public UserType createUserType(@Valid @RequestBody UserType userType){
        return userTypeService.createUserType(userType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable(value = "id") Long  user_type_id) throws ResourceNotFoundExceotion {
        return userTypeService.getUserTypeById(user_type_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType>updateUserType(@PathVariable(value = "id") Long user_type_id, @Valid @RequestBody UserType userTypeDetails) throws ResourceNotFoundExceotion {
        return userTypeService.updateUserType(user_type_id,userTypeDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserTypeById(@PathVariable(value = "id") Long user_type_id) throws ResourceNotFoundExceotion {
        return userTypeService.deleteUserTypeById(user_type_id);
    }
}