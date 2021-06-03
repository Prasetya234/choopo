package com.example.choopo.util.service;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.util.model.AuthenticationRequest;
import com.example.choopo.util.model.AuthenticationResponse;

public interface UserDetailsImpl {
    User save(UserDTO userDTO);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
