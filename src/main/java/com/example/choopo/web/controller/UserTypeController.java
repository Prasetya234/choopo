package com.example.choopo.web.controller;

import com.example.choopo.web.dto.UserTypeDTO;
import com.example.choopo.web.model.UserType;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.UserTypeImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.web.exception.ResourceNotFoundExceotion;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
@RequestMapping("/reference/user-type")
public class UserTypeController {

    @Autowired private UserTypeImpl userTypeService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;


    @GetMapping("/")
    public CommonResponse<List<UserTypeDTO>> getAll() {
        Stream<Object> userTypeList = userTypeService.getAll().stream().map(userType -> modelMapper.map(userType, UserTypeDTO.class));

        return commonResponseGenerator.successResponse(userTypeList);
    }

    @PostMapping("/")
    public CommonResponse<UserType> createUserType(@Valid @RequestBody UserType userTypeDTORequire){
//        UserType userTypeRequire = modelMapper.map(userTypeDTORequire, UserType.class);

        UserType createUserType = userTypeService.createUserType(userTypeDTORequire);

//        UserTypeDTO userTypeDTO = modelMapper.map(userTypeDTORequire, UserTypeDTO.class);

        return commonResponseGenerator.successResponse(createUserType);
    }

    @GetMapping("/{id}")
    public CommonResponse<UserTypeDTO> getUserTypeById(@PathVariable(value = "id") Long  userTypeId) throws ResourceNotFoundExceotion {
        UserType getUserTypeById = userTypeService.getUserTypeById(userTypeId);

        UserTypeDTO userTypeDTO = modelMapper.map(getUserTypeById, UserTypeDTO.class);
        return commonResponseGenerator.successResponse(userTypeDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<UserTypeDTO> updateUserType(@PathVariable(value = "id") Long userTypeId, @Valid @RequestBody UserTypeDTO userTypeDTODetails) throws ResourceNotFoundExceotion {
        UserType userTypeDetails = modelMapper.map(userTypeDTODetails, UserType.class);

        UserType updateUserType = userTypeService.updateUserType(userTypeId, userTypeDetails);

        UserTypeDTO userTypeDTO = modelMapper.map(updateUserType, UserTypeDTO.class);
        return commonResponseGenerator.successResponse(userTypeDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserTypeById(@PathVariable(value = "id") Long userTypeId) throws ResourceNotFoundExceotion {
        return userTypeService.deleteUserTypeById(userTypeId);
    }
}