package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;

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
