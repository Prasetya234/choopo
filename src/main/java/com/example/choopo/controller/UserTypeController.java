package com.example.choopo.controller;

import com.example.choopo.dto.UserTypeDTO;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import com.example.choopo.service.UserTypeImpl;
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

    @Autowired private UserTypeImpl userTypeService;

    @GetMapping("/")
    public List<UserType> getAll() {
        return userTypeService.getAll();
    }

    @PostMapping("/")
    public UserType createUserType(@Valid @RequestBody UserType userTypeRequire){
        return userTypeService.createUserType(userTypeRequire);
    }

    @GetMapping("/{id}")
    public UserType getUserTypeById(@PathVariable(value = "id") Long  userTypeId) throws ResourceNotFoundExceotion {
        return userTypeService.getUserTypeById(userTypeId);
    }

    @PutMapping("/{id}")
    public UserType updateUserType(@PathVariable(value = "id") Long userTypeId, @Valid @RequestBody UserType userTypeDetails) throws ResourceNotFoundExceotion {
        return userTypeService.updateUserType(userTypeId, userTypeDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserTypeById(@PathVariable(value = "id") Long userTypeId) throws ResourceNotFoundExceotion {
        return userTypeService.deleteUserTypeById(userTypeId);
    }
}