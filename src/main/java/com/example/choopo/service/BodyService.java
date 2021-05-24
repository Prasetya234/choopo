package com.example.choopo.service;

import com.example.choopo.dto.BodyDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
//import com.example.choopo.model.Article;
import com.example.choopo.model.Body;
import com.example.choopo.repository.BodyRepository;
import com.example.choopo.repository.BodyTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BodyService {

    @Autowired private BodyRepository bodyRepository;

    @Autowired private BodyTypeRepository bodyTypeRepository;

//    @Autowired private ModelMapper modelMapper;


    // GET ALL BODY
    public ResponseEntity<Map<String, Object>> getAll() {

            List<Body> bodyList = new ArrayList<>();

            bodyList = bodyRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", bodyList);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET BODY BY ID
    public ResponseEntity<Body> getBodyById(Long body_id) throws ResourceNotFoundExceotion {
        Optional<Body> body = Optional.ofNullable(bodyRepository.findById(body_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", body);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // BODY FIND BY ARTICLE
    public List<Body> byArticleId(String article_id) throws ResourceNotFoundExceotion{
        return bodyRepository.findByArticleId(article_id);
    }

    // POST DENGAN PARAM `ID` -> BODY TYPE
    public Body createBody (Body bodyRequest) throws ResourceNotFoundExceotion {
        Body body = bodyTypeRepository.findById(bodyRequest.getBodyType().getBodyTypeId())
                .map(bodyType -> {
                    bodyRequest.setBodyType(bodyType);
                    return bodyRequest;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND")
                );
        return bodyRepository.save(bodyRequest);
    }

    // PUT BODY
    public Body updateBody( Long body_id, Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

                body.setBodyContent(bodyDetails.getBodyContent());
                body.setArticleId(bodyDetails.getArticleId());
                final Body updatedBody = bodyRepository.save(body);
        return updatedBody;
    }

    // DELETE BODY
    public Map<String, Boolean> deleteBody(Long body_id) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

                bodyRepository.delete(body);
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
