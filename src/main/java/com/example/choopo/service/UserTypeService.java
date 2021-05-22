package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserTypeService {

    @Autowired private UserTypeRepository userTypeRepository;

    // GET ALL USER TYPE
    public ResponseEntity<Map<String, Object>> getAll() {

            List<UserType> userTypes = new ArrayList<>();

            userTypes = userTypeRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userTypes);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST USER TYPE
    public UserType createUserType(UserType userType){
        return userTypeRepository.save(userType);
    }

    // GET USER TYPE BY ID
    public ResponseEntity<UserType> getUserTypeById(Long  user_type_id) throws ResourceNotFoundExceotion {
        Optional<UserType> userType = Optional.ofNullable(userTypeRepository.findById(user_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userType);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // UPDATE USER TYPE BY ID
    public ResponseEntity<UserType> updateUserType(Long user_type_id, UserType userTypeDetails) throws ResourceNotFoundExceotion {
        UserType userType = userTypeRepository.findById(user_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER NOT FOUND " + user_type_id));

        userType.setUserTypeName(userTypeDetails.getUserTypeName());
        userType.setUserTypeCode(userTypeDetails.getUserTypeCode());
        final UserType updateUserType = userTypeRepository.save(userType);

        return ResponseEntity.ok(updateUserType);
    }

    // DELETE USER TYPE BY ID
    public Map<String, Boolean> deleteUserTypeById(Long user_type_id) throws ResourceNotFoundExceotion {
        UserType userType = userTypeRepository.findById(user_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ID NOT FOUND  " + user_type_id));

        userTypeRepository.delete(userType);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }
}
