package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAll();

    User createUser(User userRequire);

    User getUserById(Long userId) throws ResourceNotFoundExceotion;

    User updateUserById(Long userId, User userDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteUserById(Long userId) throws ResourceNotFoundExceotion;
}
