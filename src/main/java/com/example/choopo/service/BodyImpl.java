package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.repository.BodyRepository;
import com.example.choopo.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BodyImpl implements BodyService {

    @Autowired private BodyRepository bodyRepository;

    @Autowired private BodyTypeRepository bodyTypeRepository;

//    @Autowired private ModelMapper modelMapper;

    @Override
    public List<Body> getAll() {
        return bodyRepository.findAll();
    }

    @Override
    public Body getBodyById(Long bodyId) throws ResourceNotFoundExceotion {
        return bodyRepository.findById(bodyId).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND") );
    }

    @Override
    public List<Body> getBodyByArticleId(String articleId) throws ResourceNotFoundExceotion {
        return bodyRepository.findByArticleId(articleId);
    }

    @Override
    public Body createBody(Body bodyRequest) throws ResourceNotFoundExceotion {
        Body body = bodyTypeRepository.findById(Long.valueOf(bodyRequest.getBodyType()))
                .map(bodyTypeId -> {
                    bodyRequest.setBodyTypeId(bodyTypeId);
                    return bodyRequest;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));
        return bodyRepository.save(bodyRequest);
    }

    @Override
    public Body updateBodyById(Long bodyId, Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(bodyId).orElseThrow(() ->
                new ResourceNotFoundExceotion("BODY ID NOT FOUND"));

        body.setBodyContent(bodyDetails.getBodyContent());
        body.setArticleId(bodyDetails.getArticleId());
        final Body updateData = bodyRepository.save(body);
        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteBodyById(Long bodyId) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(bodyId).orElseThrow(() ->
                new ResourceNotFoundExceotion ("BODY ID NOT FOUND"));

        bodyRepository.deleteById(bodyId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }


    // CONVERT DTO TO ENTITY
//    private BodyDTO mapToDTO(Body body) {
//        BodyDTO bodyDTO = modelMapper.map(body, BodyDTO.class);
//
//        return  bodyDTO;
//    }
//
//
//    // CONVERT DTO TO ENTITY
//    private Body mapToEntity(BodyDTO bodyDTO) {
//        Body body = modelMapper.map(bodyDTO, Body.class);
//
//        return  body;
//    }
}
