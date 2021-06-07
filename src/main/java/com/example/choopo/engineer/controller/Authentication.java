package com.example.choopo.engineer.controller;

import com.example.choopo.engineer.dto.TemporaryTokenDTO;
import com.example.choopo.web.dto.UserDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.User;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.engineer.model.AunthenticationRequest;
import com.example.choopo.engineer.model.TemporaryToken;
import com.example.choopo.engineer.service.TemporaryTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class Authentication {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommonResponseGenerator commonResponseGenerator;

    @Autowired
    private TemporaryTokenService temporaryTokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse<AunthenticationRequest> createAuthenticationToken(HttpServletRequest request) throws Exception {

        return commonResponseGenerator.successResponse(temporaryTokenService.login(request));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse<UserDTO> saveUser(@RequestBody @Valid UserDTO userDTORequire) throws ResourceNotFoundExceotion {
        User userDTO = modelMapper.map(userDTORequire, User.class);

        User user = temporaryTokenService.register(userDTO);

        UserDTO userDTO1 = modelMapper.map(user, UserDTO.class);

        return commonResponseGenerator.successResponse(userDTO1);
    }

    @RequestMapping(value = "/get-token", method = RequestMethod.GET)
    public CommonResponse<TemporaryTokenDTO> getTokenList() {
        Stream<Object> tokenList = temporaryTokenService.getTokenList().stream().map(temporaryToken -> modelMapper.map(temporaryToken, TemporaryTokenDTO.class));
        return  commonResponseGenerator.successResponse(tokenList);
    }
}

