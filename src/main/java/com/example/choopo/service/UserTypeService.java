package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.model.UserType;

import java.util.List;
import java.util.Map;

public interface UserTypeService {

    List<UserType> getAll();

    UserType createUserType(UserType bodyTypeRequire);

    UserType getUserTypeById(Long userTypeId) throws ResourceNotFoundExceotion;

    UserType updateUserType(Long userTypeId, UserType userTypeDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteUserTypeById(Long userTypeId) throws ResourceNotFoundExceotion;
}
