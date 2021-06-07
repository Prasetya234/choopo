package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.UserType;

import java.util.List;
import java.util.Map;

public interface UserTypeService {

    List<UserType> getAll();

    UserType createUserType(UserType bodyTypeRequire);

    UserType getUserTypeById(Long userTypeId) throws ResourceNotFoundExceotion;

    UserType updateUserType(Long userTypeId, UserType userTypeDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteUserTypeById(Long userTypeId) throws ResourceNotFoundExceotion;
}
