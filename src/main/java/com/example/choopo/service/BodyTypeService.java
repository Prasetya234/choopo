package com.example.choopo.service;

import com.example.choopo.dto.BodyTypeDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.repository.BodyTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BodyTypeService {

    @Autowired private BodyTypeRepository bodyTypeRepository;

    @Autowired private ModelMapper modelMapper;

    // GET ALL BODY TYPE
    public ResponseEntity<Map<String, Object>> getAll() {

            List<BodyType> bodyTypes = new ArrayList<>();

            bodyTypes = bodyTypeRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", bodyTypes);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET BODY TYPE BY ID
    public ResponseEntity<BodyTypeDTO> getBodyTypeById(Long body_type_id) throws ResourceNotFoundExceotion {
        Optional<BodyType> bodyType = Optional.ofNullable(bodyTypeRepository.findById(body_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", bodyType);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // POST BODY TYPE
    public BodyTypeDTO createBodyType(BodyTypeDTO bodyTypeDTO) {
        // convert dto to entity
        BodyType bodyType = mapToEntity(bodyTypeDTO);
        BodyType newBodyType = bodyTypeRepository.save(bodyType);

        // convert entity to dto
        BodyTypeDTO postResponse = mapToDTO(newBodyType);
        return postResponse;
    }

    // UPDATE BODY TYPE BY ID
    public BodyTypeDTO updateBodyType(Long body_type_id, BodyTypeDTO bodyTypeDTODetils) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND " + body_type_id));

        bodyType.setBodyTypeCode(bodyTypeDTODetils.getBodyTypeCode());
        bodyType.setBodyTypeName(bodyTypeDTODetils.getBodyTypeName());
        final BodyType updateBodyType = bodyTypeRepository.save(bodyType);

        return mapToDTO(updateBodyType);
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

    // CONVERT DTO TO ENTITY
    private BodyTypeDTO mapToDTO(BodyType bodyType) {
        BodyTypeDTO  bodyTypeDTO = modelMapper.map(bodyType, BodyTypeDTO.class);

        return  bodyTypeDTO ;
    }


    // CONVERT DTO TO ENTITY
    private BodyType mapToEntity(BodyTypeDTO bodyTypeDTO) {
        BodyType bodyType = modelMapper.map(bodyTypeDTO, BodyType.class);

        return  bodyType;
    }
}
