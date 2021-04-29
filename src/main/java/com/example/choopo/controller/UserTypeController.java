package com.example.choopo.controller;

import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.exception.ResourceNotFoundExceotion;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reference/user-type")
public class UserTypeController {
    @Autowired
    private UserTypeRepository userTypeRepository;

    @GetMapping("/")
    public List<UserType> getAllUserType(){
        return userTypeRepository.findAll();
    }

    @PostMapping("/")
    public UserType createUserType(@Valid @RequestBody UserType userType){
        return userTypeRepository.save(userType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable(value = "id") Long  user_type_id)
            throws ResourceNotFoundExceotion {
        UserType userType = userTypeRepository.findById(user_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("USERTYPE NOTFOUND"));
        return ResponseEntity.ok().body(userType);
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
