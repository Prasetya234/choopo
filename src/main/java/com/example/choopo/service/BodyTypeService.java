package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BodyTypeService {

    @Autowired private BodyTypeRepository bodyTypeRepository;

    // GET ALL BODY TYPE
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<BodyType> bodyTypes = new ArrayList<>();

            bodyTypes = bodyTypeRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", bodyTypes);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET BODY TYPE BY ID
    public ResponseEntity<BodyType> getBodyTypeById(Long body_type_id) throws ResourceNotFoundExceotion {
        Optional<BodyType> bodyType = Optional.ofNullable(bodyTypeRepository.findById(body_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", bodyType);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    // POST BODY TYPE
    public BodyType createBodyType(BodyType bodyType) {
        return bodyTypeRepository.save(bodyType);
    }

    // UPDATE BODY TYPE BY ID
    public ResponseEntity<BodyType> updateBodyType(Long body_type_id, BodyType bodyTypeDetails) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND " + body_type_id));

        bodyType.setBodyTypeName(bodyTypeDetails.getBodyTypeName());
        bodyType.setBodyTypeCode(bodyTypeDetails.getBodyTypeCode());

        final BodyType updatedBodyType = bodyTypeRepository.save(bodyType);
        return ResponseEntity.ok(updatedBodyType);
    }

    //DELETE BODY TYPE BY ID
    public Map<String, Boolean> deleteBodyType(Long body_type_id) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND " + body_type_id));

        bodyTypeRepository.delete(bodyType);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
