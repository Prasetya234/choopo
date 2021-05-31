package com.example.choopo.util.controller;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.util.service.MyUserDetailsService;
import com.example.choopo.util.model.AuthenticationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class Authentication {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommonResponseGenerator commonResponseGenerator;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse<AuthenticationRequest> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        return commonResponseGenerator.successResponse(userDetailsService.login(authenticationRequest));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse<UserDTO> saveUser(@RequestBody @Valid UserDTO userDTO) {

        User userRequest = modelMapper.map(userDTO, User.class);
        UserDTO fin = modelMapper.map(userRequest, UserDTO.class);
        return commonResponseGenerator.successResponse(userDetailsService.save(fin));
    }
}
