package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAll();

    User createUser(User userRequire) throws ResourceNotFoundExceotion ;

    User getUserById(Long userId) throws ResourceNotFoundExceotion;

    User updateUserById(Long userId, User userDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteUserById(Long userId) throws ResourceNotFoundExceotion;
}
