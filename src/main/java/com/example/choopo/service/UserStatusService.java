package com.example.choopo.service;

import com.example.choopo.dto.UserStatusDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserStatus;
import com.example.choopo.repository.UserStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserStatusService {

    @Autowired private UserStatusRepository userStatusRepository;
    @Autowired private ModelMapper modelMapper;


    // GET ALL USER STATUS
    public ResponseEntity<Map<String, Object>> getAll() {

            List<UserStatus> userStatuses = new ArrayList<>();

            userStatuses = userStatusRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("status","SUCCESS");
            response.put("message","SUCCESS");
            response.put("content", userStatuses);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST USER STATUS
    public UserStatusDTO createUserStatus(UserStatusDTO userStatusDTO){
        UserStatus userStatus = mapToEntity(userStatusDTO);
        UserStatus newUserStatus = userStatusRepository.save(userStatus);

        UserStatusDTO postResponse = mapToDTO(newUserStatus);
        return postResponse;
    }

    // GET USER STATUS BY ID
    public ResponseEntity<UserStatusDTO> getUserStatusById(Long user_status_id) throws ResourceNotFoundExceotion {

        Optional<UserStatus> userStatus = Optional.ofNullable(userStatusRepository.findById(user_status_id)
                .orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userStatus);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // UDPATE USER STATUS BY ID
    public UserStatusDTO updateUserStatusById(Long user_status_id, UserStatusDTO userStatusDTODetails) throws ResourceNotFoundExceotion {
        UserStatus userStatus = userStatusRepository.findById(user_status_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));

        userStatus.setUserStatusCode(userStatusDTODetails.getUserStatusCode());
        userStatus.setUserStatusName(userStatusDTODetails.getUserStatusName());
        final UserStatus updateUserStatusById = userStatusRepository.save(userStatus);

        return mapToDTO(updateUserStatusById);
    }

    // DELETE USER STATUS BY ID
    public Map<String, Boolean> deleteUserStatusById(Long user_status_id) throws ResourceNotFoundExceotion {
        UserStatus userStatus= userStatusRepository.findById(user_status_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));

        userStatusRepository.delete(userStatus);

        Map<java.lang.String, java.lang.Boolean> response = new HashMap<>();
        response.put("DELETED", java.lang.Boolean.TRUE);

        return response;
    }

    // CONVERT DTO TO ENTITY
    private UserStatusDTO mapToDTO(UserStatus userStatus) {
        UserStatusDTO userStatusDTO = modelMapper.map(userStatus, UserStatusDTO.class);

        return  userStatusDTO;
    }


    // CONVERT DTO TO ENTITY
    private UserStatus mapToEntity(UserStatusDTO userStatusDTO) {
        UserStatus userStatus = modelMapper.map(userStatusDTO, UserStatus.class);

        return  userStatus;
    }

}
