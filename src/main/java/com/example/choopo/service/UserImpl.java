package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserImpl implements UserService{

    @Autowired private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User userRequire) {
        return userRepository.save(userRequire);
    }

    @Override
    public User getUserById(Long userId) throws ResourceNotFoundExceotion {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOT FOUND"));
    }

    @Override
    public User updateUserById(Long userId, User userDetails) throws ResourceNotFoundExceotion {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceotion("USER ID NOT FOUND"));

        user.setUserType(userDetails.getUserType());
        user.setUsername(userDetails.getUsername());
        user.setUserCode(userDetails.getUserCode());
        user.setPassword(userDetails.getPassword());
        user.setUserStatus(userDetails.getUserStatus());

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
