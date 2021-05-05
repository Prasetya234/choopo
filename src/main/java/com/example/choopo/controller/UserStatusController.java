package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.UserStatus;
import com.example.choopo.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-status")
public class UserStatusController {

    @Autowired
    private UserStatusRepository userStatusRepository;

    @GetMapping("/")
    public List<UserStatus> getAllUserStatus(){
        return userStatusRepository.findAll();
    }

    @PostMapping("/")
    public UserStatus createUserStatus(@Valid @RequestBody UserStatus userStatus){
        return userStatusRepository.save(userStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatus> getUserStatusById(@PathVariable (value = "id")Long user_status_id)
            throws ResourceNotFoundExceotion{
        UserStatus userStatus = userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));
        return ResponseEntity.ok().body(userStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserStatus> updateUserStatusById(@PathVariable (value = "id")Long user_status_id,@Valid @RequestBody UserStatus userStatusDetails)
            throws ResourceNotFoundExceotion {
        UserStatus userStatus = userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));

        userStatus.setUser_status_code(userStatusDetails.getUser_status_code());
        userStatus.setUser_status_name(userStatusDetails.getUser_status_name());
        final UserStatus updateUserStatusById = userStatusRepository.save(userStatus);
        return ResponseEntity.ok(updateUserStatusById);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserStatusById(@PathVariable (value = "id")Long user_status_id)
            throws ResourceNotFoundExceotion {
        UserStatus userStatus= userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));

        userStatusRepository.delete(userStatus);
        Map<String , Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
