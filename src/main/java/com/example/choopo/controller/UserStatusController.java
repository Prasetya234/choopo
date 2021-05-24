package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.model.UserStatus;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.UserStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-status")
public class UserStatusController {

    @Autowired private UserStatusImpl userStatusService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<UserStatus>> getAll() {
        List<UserStatus> userStatusList = userStatusService.getAll();

        return commonResponseGenerator.successResponse(userStatusList);
    }

    @PostMapping("/")
    public CommonResponse<UserStatus> createUserStatus(@Valid @RequestBody UserStatus userStatusRequire){
        UserStatus createUserStatus = userStatusService.createUserStatus(userStatusRequire);

        return commonResponseGenerator.successResponse(createUserStatus);
    }

    @GetMapping("/{id}")
    public CommonResponse<UserStatus> getUserStatusById(@PathVariable (value = "id")Long userStatusId) throws ResourceNotFoundExceotion{
        UserStatus getUserStatusById = userStatusService.getUserStatusById(userStatusId);

        return commonResponseGenerator.successResponse(getUserStatusById);
    }

    @PutMapping("/{id}")
    public CommonResponse<UserStatus> updateUserStatusById(@PathVariable (value = "id")Long userStatusId, @Valid @RequestBody UserStatus userStatusDetails) throws ResourceNotFoundExceotion {
        UserStatus updateUserStatusById = userStatusService.updateUserStatusById(userStatusId,userStatusDetails);

        return commonResponseGenerator.successResponse(updateUserStatusById);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserStatusById(@PathVariable (value = "id")Long userStatusId) throws ResourceNotFoundExceotion {
        return userStatusService.deleteUserStatusById(userStatusId);
    }
}