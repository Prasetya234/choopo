package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;

import java.util.List;
import java.util.Map;

public interface BodyTypeService {

    List<BodyType> getAll();

    BodyType getBodyTypeById(Long bodyTypeId) throws ResourceNotFoundExceotion;

    BodyType createBodyType(BodyType bodyTypeRequire);

    BodyType updateBodyTypeById(Long bodyTypeId, BodyType bodyTypeDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteBodyTypeById(Long bodyTypeId) throws ResourceNotFoundExceotion;
}
