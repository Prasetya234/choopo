package com.example.choopo.util.controller;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.util.repository.AuthenticationResponseRepository;
import com.example.choopo.util.service.MyUserDetailsService;
import com.example.choopo.util.JwtUtil;
import com.example.choopo.util.model.AuthenticationRequest;
import com.example.choopo.util.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class Authentication {

    @Autowired
    private CommonResponseGenerator commonResponseGenerator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationResponseRepository authenticationResponseRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse<AuthenticationRequest> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);

        authenticationResponseRepository.save(authenticationResponse);

        return commonResponseGenerator.successResponse(authenticationResponse);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse<User> saveUser(@RequestBody @Valid UserDTO userDTO) {

        return commonResponseGenerator.successResponse(userDetailsService.save(userDTO));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
