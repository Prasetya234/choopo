package com.example.choopo.controller;

import com.example.choopo.dto.UserStatusDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.model.UserStatus;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.UserStatusImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/reference/user-status")
public class UserStatusController {

    @Autowired private UserStatusImpl userStatusService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<UserStatusDTO>> getAll() {
        Stream<Object> userStatusList = userStatusService.getAll().stream().map(userStatus -> modelMapper.map(userStatus, UserStatusDTO.class));

        return commonResponseGenerator.successResponse(userStatusList);
    }

    @PostMapping("/")
    public CommonResponse<UserStatusDTO> createUserStatus(@Valid @RequestBody UserStatusDTO userStatusDTORequire){
        UserStatus userStatusRequire = modelMapper.map(userStatusDTORequire, UserStatus.class);

        UserStatus createUserStatus = userStatusService.createUserStatus(userStatusRequire);

        UserStatusDTO userStatusDTO = modelMapper.map(createUserStatus, UserStatusDTO.class);

        return commonResponseGenerator.successResponse(userStatusDTO);
    }

    @GetMapping("/{id}")
    public CommonResponse<UserStatusDTO> getUserStatusById(@PathVariable (value = "id")Long userStatusId) throws ResourceNotFoundExceotion{
        UserStatus getUserStatusById = userStatusService.getUserStatusById(userStatusId);

        UserStatusDTO userStatusDTO = modelMapper.map(getUserStatusById, UserStatusDTO.class);

        return commonResponseGenerator.successResponse(userStatusDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<UserStatusDTO> updateUserStatusById(@PathVariable (value = "id")Long userStatusId, @Valid @RequestBody UserStatusDTO userStatusDTODetails) throws ResourceNotFoundExceotion {
        UserStatus userStatusDetails = modelMapper.map(userStatusDTODetails, UserStatus.class);

        UserStatus updateUserStatusById = userStatusService.updateUserStatusById(userStatusId,userStatusDetails);

        UserStatusDTO userStatusDTO = modelMapper.map(updateUserStatusById, UserStatusDTO.class);

        return commonResponseGenerator.successResponse(userStatusDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserStatusById(@PathVariable (value = "id")Long userStatusId) throws ResourceNotFoundExceotion {
        return userStatusService.deleteUserStatusById(userStatusId);
    }
}