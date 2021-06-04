package com.example.choopo.util.service;

import com.example.choopo.model.User;
import com.example.choopo.util.model.AunthenticationRequest;
import com.example.choopo.util.model.TemporaryToken;

import java.util.List;

public interface TemporaryTokenImpl {
    User register(User userDTO);
    Object login(AunthenticationRequest aunthenticationRequest) throws Exception;
    List<TemporaryToken> getTokenList();
}
