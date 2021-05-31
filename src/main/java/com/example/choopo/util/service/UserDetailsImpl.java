package com.example.choopo.util.service;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.util.model.AuthenticationRequest;
import com.example.choopo.util.model.AuthenticationResponse;

public abstract class UserDetailsImpl {
    public abstract User save(UserDTO userDTO);
    public abstract AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
