package com.example.choopo.controller;

import com.example.choopo.model.UserType;
import com.example.choopo.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-type")
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
}
