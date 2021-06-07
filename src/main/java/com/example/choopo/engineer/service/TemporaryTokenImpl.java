package com.example.choopo.engineer.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.User;
import com.example.choopo.engineer.model.TemporaryToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TemporaryTokenImpl {
    User register(User userDTO) throws ResourceNotFoundExceotion;
    Object login(HttpServletRequest request) throws Exception;
    List<TemporaryToken> getTokenList();
}
