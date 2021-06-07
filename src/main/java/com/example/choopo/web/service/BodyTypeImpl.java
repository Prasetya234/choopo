package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.BodyType;
import com.example.choopo.web.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BodyTypeImpl implements BodyTypeService{

    @Autowired private BodyTypeRepository bodyTypeRepository;


    @Override
    public List<BodyType> getAll() {
        return bodyTypeRepository.findAll();
    }

    @Override
    public BodyType getBodyTypeById(Long bodyTypeId) throws ResourceNotFoundExceotion {
        return bodyTypeRepository.findById(bodyTypeId).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));
    }

    @Override
    public BodyType createBodyType(BodyType bodyTypeRequire) {
        return bodyTypeRepository.save(bodyTypeRequire);
    }

    @Override
    public BodyType updateBodyTypeById(Long bodyTypeId, BodyType bodyTypeDetails) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(bodyTypeId).orElseThrow(() ->
                new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));

        bodyType.setBodyTypeName(bodyTypeDetails.getBodyTypeName());
        bodyType.setBodyTypeCode(bodyTypeDetails.getBodyTypeCode());
        final BodyType updateData = bodyTypeRepository.save(bodyType);
        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteBodyTypeById(Long bodyTypeId) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(bodyTypeId).orElseThrow(() ->
                new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));

        bodyTypeRepository.delete(bodyType);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }


}
