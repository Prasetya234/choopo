package com.example.choopo.util.controller;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.util.model.AunthenticationRequest;
import com.example.choopo.util.model.TemporaryToken;
import com.example.choopo.util.service.TemporaryTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class Authentication {

//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    private CommonResponseGenerator commonResponseGenerator;

    @Autowired
    private TemporaryTokenService temporaryTokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse<AunthenticationRequest> createAuthenticationToken(HttpServletRequest request) throws Exception {

        return commonResponseGenerator.successResponse(temporaryTokenService.login(request));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse<User> saveUser(@RequestBody @Valid User user) throws ResourceNotFoundExceotion {

        return commonResponseGenerator.successResponse(temporaryTokenService.register(user));
    }

    @RequestMapping(value = "/get-token", method = RequestMethod.GET)
    public CommonResponse<TemporaryToken> getTokenList() {
        return  commonResponseGenerator.successResponse(temporaryTokenService.getTokenList());
    }
}
