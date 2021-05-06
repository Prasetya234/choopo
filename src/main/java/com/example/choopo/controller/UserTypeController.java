package com.example.choopo.controller;

import com.example.choopo.model.Topic;
import com.example.choopo.model.UserStatus;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.exception.ResourceNotFoundExceotion;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-type")
public class UserTypeController {
    @Autowired
    private UserTypeRepository userTypeRepository;

//    @GetMapping("/")
//    public List<UserType> getAllUserType(){
//        return userTypeRepository.findAll();
//    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<UserType> userTypes = new ArrayList<>();

            userTypes = userTypeRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userTypes);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public UserType createUserType(@Valid @RequestBody UserType userType){
        return userTypeRepository.save(userType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable(value = "id") Long  user_type_id) throws ResourceNotFoundExceotion {
//        UserType userType = userTypeRepository.findById(user_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("USERTYPE NOTFOUND"));
//        return ResponseEntity.ok().body(userType);
//    }

        Optional<UserType> userType = Optional.ofNullable(userTypeRepository.findById(user_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", userType);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType> updateEmployee(@PathVariable(value = "id") Long user_type_id, @Valid @RequestBody UserType userTypeDetails)
            throws ResourceNotFoundExceotion {
        UserType userType = userTypeRepository.findById(user_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("USER NOT FOUND " + user_type_id));

        userType.setUser_type_name(userTypeDetails.getUser_type_name());
        userType.setUser_type_code(userTypeDetails.getUser_type_code());
        final UserType updatedEmployee = userTypeRepository.save(userType);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserTypeById(@PathVariable(value = "id") Long user_type_id)
            throws ResourceNotFoundExceotion {
        UserType userType = userTypeRepository.findById(user_type_id)
                .orElseThrow(() -> new ResourceNotFoundExceotion("ID NOT FOUND  " + user_type_id));

        userTypeRepository.delete(userType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
