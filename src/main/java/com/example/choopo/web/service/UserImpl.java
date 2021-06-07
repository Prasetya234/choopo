package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.User;
import com.example.choopo.web.repository.UserRepository;
import com.example.choopo.web.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserImpl implements UserService{

    @Autowired private UserRepository userRepository;

    @Autowired private UserTypeRepository userTypeRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User userRequire) throws ResourceNotFoundExceotion{
        User userType = userTypeRepository.findById(Long.valueOf(userRequire.getUserType()))
                .map(user -> {
                    userRequire.setUserTypeId(user);
                    return userRequire;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
        return userRepository.save(userRequire);
    }

    @Override
    public User getUserById(Long userId) throws ResourceNotFoundExceotion {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOT FOUND"));
    }

    @Override
    public User updateUserById(Long userId, User userDetails) throws ResourceNotFoundExceotion {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOT FOUND"));

        user.setUsername(userDetails.getUsername());
        user.setUserCode(userDetails.getUserCode());
        user.setPassword(userDetails.getPassword());
        user.setUserStatus(userDetails.getUserStatus());
        user.setUserType(userDetails.getUserType());
        User userType1 = userTypeRepository.findById(Long.valueOf(userDetails.getUserType())).map(userType -> {
            userDetails.setUserTypeId(userType);
            return userDetails;
        }).orElseThrow(() ->
                new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));
        user.setUserTypeId(userType1.getUserTypeId());
        final User updateData = userRepository.save(user);
        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteUserById(Long userId) throws ResourceNotFoundExceotion {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOT FOUND"));

        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
