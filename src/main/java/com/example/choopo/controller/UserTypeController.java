package com.example.choopo.controller;

import com.example.choopo.dto.UserTypeDTO;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.UserTypeImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.exception.ResourceNotFoundExceotion;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
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
    public CommonResponse<UserTypeDTO> createUserType(@Valid @RequestBody UserTypeDTO userTypeDTORequire){
        UserType userTypeRequire = modelMapper.map(userTypeDTORequire, UserType.class);

        UserType createUserType = userTypeService.createUserType(userTypeRequire);

        UserTypeDTO userTypeDTO = modelMapper.map(userTypeDTORequire, UserTypeDTO.class);

        return commonResponseGenerator.successResponse(userTypeDTO);
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