package com.example.choopo.controller;

import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.UserTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.exception.ResourceNotFoundExceotion;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-type")
public class UserTypeController {

    @Autowired private UserTypeRepository userTypeRepository;

    @Autowired private UserTypeImpl userTypeService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;


    @GetMapping("/")
    public CommonResponse<List<UserType>> getAll() {
        List<UserType> userTypeList = userTypeService.getAll();

        return commonResponseGenerator.successResponse(userTypeList);
    }

    @PostMapping("/")
    public CommonResponse<UserType> createUserType(@Valid @RequestBody UserType userTypeRequire){
        UserType createUserType = userTypeService.createUserType(userTypeRequire);

        return commonResponseGenerator.successResponse(createUserType);
    }

    @GetMapping("/{id}")
    public CommonResponse<UserType> getUserTypeById(@PathVariable(value = "id") Long  userTypeId) throws ResourceNotFoundExceotion {
        UserType getUserTypeById = userTypeService.getUserTypeById(userTypeId);

        return commonResponseGenerator.successResponse(getUserTypeById);
    }

    @PutMapping("/{id}")
    public CommonResponse<UserType> updateUserType(@PathVariable(value = "id") Long userTypeId, @Valid @RequestBody UserType userTypeDetails) throws ResourceNotFoundExceotion {
        UserType updateUserType = userTypeService.updateUserType(userTypeId, userTypeDetails);

        return commonResponseGenerator.successResponse(updateUserType);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserTypeById(@PathVariable(value = "id") Long userTypeId) throws ResourceNotFoundExceotion {
        return userTypeService.deleteUserTypeById(userTypeId);
    }
}