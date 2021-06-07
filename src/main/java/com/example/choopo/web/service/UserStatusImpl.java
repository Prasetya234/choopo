package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.UserStatus;
import com.example.choopo.web.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserStatusImpl implements UserStatusService{

    @Autowired private UserStatusRepository userStatusRepository;

    @Override
    public List<UserStatus> getAll() {
        return userStatusRepository.findAll();
    }

    @Override
    public UserStatus createUserStatus(UserStatus userStatusReference) {
        return userStatusRepository.save(userStatusReference);
    }

    @Override
    public UserStatus getUserStatusById(Long userStatusId) throws ResourceNotFoundExceotion {
        return userStatusRepository.findById(userStatusId).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOT FOUND"));
    }

    @Override
    public UserStatus updateUserStatusById(Long userStatusId, UserStatus userStatusDetils) throws ResourceNotFoundExceotion {
        UserStatus userStatus = userStatusRepository.findById(userStatusId).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));

        userStatus.setUserStatusName(userStatusDetils.getUserStatusName());
        userStatus.setUserStatusCode(userStatusDetils.getUserStatusCode());
        final UserStatus updateData = userStatusRepository.save(userStatus);

        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteUserStatusById(Long userStatusId) throws ResourceNotFoundExceotion {
        UserStatus userStatus = userStatusRepository.findById(userStatusId).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));

        userStatusRepository.delete(userStatus);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }


//    // CONVERT DTO TO ENTITY
//    private UserStatusDTO mapToDTO(UserStatus userStatus) {
//        UserStatusDTO userStatusDTO = modelMapper.map(userStatus, UserStatusDTO.class);
//
//        return  userStatusDTO;
//    }
//
//
//    // CONVERT DTO TO ENTITY
//    private UserStatus mapToEntity(UserStatusDTO userStatusDTO) {
//        UserStatus userStatus = modelMapper.map(userStatusDTO, UserStatus.class);
//
//        return  userStatus;
//    }

}
