package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.BodyType;

import java.util.List;
import java.util.Map;

public interface BodyTypeService {

    List<BodyType> getAll();

    BodyType getBodyTypeById(Long bodyTypeId) throws ResourceNotFoundExceotion;

    BodyType createBodyType(BodyType bodyTypeRequire);

    BodyType updateBodyTypeById(Long bodyTypeId, BodyType bodyTypeDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteBodyTypeById(Long bodyTypeId) throws ResourceNotFoundExceotion;
}
