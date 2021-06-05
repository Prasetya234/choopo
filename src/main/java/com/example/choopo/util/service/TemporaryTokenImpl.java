package com.example.choopo.util.service;

import com.example.choopo.model.User;
import com.example.choopo.util.model.AunthenticationRequest;
import com.example.choopo.util.model.TemporaryToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TemporaryTokenImpl {
    User register(User userDTO);
    Object login(HttpServletRequest request) throws Exception;
    List<TemporaryToken> getTokenList();
}
