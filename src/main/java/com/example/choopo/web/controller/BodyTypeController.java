package com.example.choopo.web.controller;

import com.example.choopo.web.dto.BodyTypeDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.BodyType;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.BodyTypeImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
@RequestMapping("/reference/body-type")
public class BodyTypeController {

    @Autowired private BodyTypeImpl bodyTypeService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<BodyTypeDTO> getAll() {
        Stream<Object> bodyTypeList = bodyTypeService.getAll()
                .stream()
                .map(bodyType -> modelMapper.map(bodyType, BodyTypeDTO.class));

        return commonResponseGenerator.successResponse(bodyTypeList);
    }

    @GetMapping("/{id}")
    public CommonResponse<BodyTypeDTO> getBodyTypeById(@PathVariable(value = "id") Long bodyTypeId) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeService.getBodyTypeById(bodyTypeId);

        BodyTypeDTO bodyTypeDTO = modelMapper.map(bodyType, BodyTypeDTO.class);

        return commonResponseGenerator.successResponse(bodyTypeDTO);
    }

    @PostMapping("/")
    public CommonResponse<BodyTypeDTO> createBodyType(@Valid @RequestBody BodyTypeDTO bodyTypeDTORequire) {
        BodyType bodyTypeRequire = modelMapper.map(bodyTypeDTORequire , BodyType.class);
        BodyType bodyType = bodyTypeService.createBodyType(bodyTypeRequire);

        BodyTypeDTO bodyTypeDTO = modelMapper.map(bodyType, BodyTypeDTO.class);
        return commonResponseGenerator.successResponse(bodyTypeDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<BodyType> updateBodyType(@PathVariable(value = "id") Long bodyTypeId, @Valid @RequestBody BodyType bodyTypeDTODetils) throws ResourceNotFoundExceotion {
        BodyType bodyTypeDetails = modelMapper.map(bodyTypeDTODetils, BodyType.class);
        BodyType bodyType = bodyTypeService.updateBodyTypeById(bodyTypeId,bodyTypeDetails);

        BodyTypeDTO bodyTypeDTO = modelMapper.map(bodyType,BodyTypeDTO.class);

        return commonResponseGenerator.successResponse(bodyTypeDTO);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyType(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.deleteBodyTypeById(body_type_id);
    }
}