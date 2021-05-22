package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.repository.BodyRepository;
import com.example.choopo.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BodyService {

    @Autowired private BodyRepository bodyRepository;

    @Autowired private BodyTypeRepository bodyTypeRepository;

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

    // GET BY ID BODY
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
    public Body createBody(Long body_type_id,  Body bodyRequest) throws ResourceNotFoundExceotion {
        return bodyTypeRepository.findById(body_type_id)
                .map(bodyType -> {
                    bodyRequest.setBodyType(bodyType);
                    return bodyRepository.save(bodyRequest);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND")
                );
    }

    // PUT BODY
    public ResponseEntity<Body> updateBody( Long body_id, Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

                body.setBodyContent(bodyDetails.getBodyContent());
                body.setArticleId(bodyDetails.getArticleId());
                final Body updatedBody = bodyRepository.save(body);
        return ResponseEntity.ok(updatedBody);
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
}
