package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserStatus;

import java.util.List;
import java.util.Map;

public interface UserStatusService {

    List<UserStatus> getAll();

    UserStatus createUserStatus(UserStatus userStatusReference);

    UserStatus getUserStatusById(Long userStatusId) throws ResourceNotFoundExceotion;

    UserStatus updateUserStatusById(Long userStatusId, UserStatus userStatusDetils) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteUserStatusById(Long userStatusId) throws ResourceNotFoundExceotion;
}
