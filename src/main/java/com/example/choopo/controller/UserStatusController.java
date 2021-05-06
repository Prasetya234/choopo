package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.model.UserStatus;
import com.example.choopo.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-status")
public class UserStatusController {

    @Autowired
    private UserStatusRepository userStatusRepository;

//    @GetMapping("/")
//    public List<UserStatus> getAllUserStatus(){
//        return userStatusRepository.findAll();
//    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<UserStatus> userStatuses = new ArrayList<>();

            userStatuses = userStatusRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("status","SUCCESS");
            response.put("message","SUCCESS");
            response.put("content", userStatuses);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public UserStatus createUserStatus(@Valid @RequestBody UserStatus userStatus){
        return userStatusRepository.save(userStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatus> getUserStatusById(@PathVariable (value = "id")Long user_status_id) throws ResourceNotFoundExceotion{
//        UserStatus userStatus = userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOTFOUND"));
//        return ResponseEntity.ok().body(userStatus);
//    }

        Optional<UserStatus> userStatus = Optional.ofNullable(userStatusRepository.findById(user_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER STATUS ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userStatus);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("USER STATUS ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
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
