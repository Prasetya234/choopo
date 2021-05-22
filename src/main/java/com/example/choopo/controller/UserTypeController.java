package com.example.choopo.controller;

import com.example.choopo.dto.UserTypeDTO;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import com.example.choopo.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.choopo.exception.ResourceNotFoundExceotion;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/user-type")
public class UserTypeController {

    @Autowired private UserTypeRepository userTypeRepository;

    @Autowired private UserTypeService userTypeService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return userTypeService.getAll();
    }

    @PostMapping("/")
    public UserTypeDTO createUserType(@Valid @RequestBody UserTypeDTO userTypeDTO){
        return userTypeService.createUserType(userTypeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTypeDTO> getUserTypeById(@PathVariable(value = "id") Long  user_type_id) throws ResourceNotFoundExceotion {
        return userTypeService.getUserTypeById(user_type_id);
    }

    @PutMapping("/{id}")
    public UserTypeDTO updateUserType(@PathVariable(value = "id") Long user_type_id, @Valid @RequestBody UserTypeDTO userTypeDTODetails) throws ResourceNotFoundExceotion {
        return userTypeService.updateUserType(user_type_id,userTypeDTODetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUserTypeById(@PathVariable(value = "id") Long user_type_id) throws ResourceNotFoundExceotion {
        return userTypeService.deleteUserTypeById(user_type_id);
    }
}