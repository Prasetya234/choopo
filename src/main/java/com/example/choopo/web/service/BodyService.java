package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Body;

import java.util.List;
import java.util.Map;

public interface BodyService {

    List<Body> getAll();

    Body getBodyById(Long bodyId) throws ResourceNotFoundExceotion;

    List<Body> getBodyByArticleId(String articleId) throws ResourceNotFoundExceotion;

    Body createBody(Body bodyRequest) throws ResourceNotFoundExceotion;

    Body updateBodyById(Long bodyId, Body bodyDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteBodyById(Long bodyId) throws ResourceNotFoundExceotion;


}
