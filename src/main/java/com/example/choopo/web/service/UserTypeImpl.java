package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.UserType;
import com.example.choopo.web.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserTypeImpl implements UserTypeService{

    @Autowired private UserTypeRepository userTypeRepository;

    @Override
    public List<UserType> getAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType createUserType(UserType userTypeRequire) {
        return userTypeRepository.save(userTypeRequire);
    }

    @Override
    public UserType getUserTypeById(Long userTypeId) throws ResourceNotFoundExceotion {
        return  userTypeRepository.findById(userTypeId).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));
    }

    @Override
    public UserType updateUserType(Long userTypeId, UserType userTypeDetails) throws ResourceNotFoundExceotion {
        UserType userType = userTypeRepository.findById(userTypeId).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));

        userType.setUserTypeName(userTypeDetails.getUserTypeName());
        userType.setUserTypeCode(userTypeDetails.getUserTypeCode());
        final UserType updateData = userTypeRepository.save(userType);

        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteUserTypeById(Long userTypeId) throws ResourceNotFoundExceotion {

        UserType userType = userTypeRepository.findById(userTypeId).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));

        userTypeRepository.delete(userType);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }


//    // CONVERT DTO TO ENTITY
//    private UserTypeDTO mapToDTO(UserType userType) {
//        UserTypeDTO userTypeDTO = modelMapper.map(userType, UserTypeDTO.class);
//
//        return  userTypeDTO;
//    }
//
//
//    // CONVERT DTO TO ENTITY
//    private UserType mapToEntity(UserTypeDTO userTypeDTO) {
//        UserType userType = modelMapper.map(userTypeDTO, UserType.class);
//
//        return  userType;
//    }
}
