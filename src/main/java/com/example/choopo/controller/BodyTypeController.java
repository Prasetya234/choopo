package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.BodyTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/body-type")
public class BodyTypeController {

    @Autowired private BodyTypeImpl bodyTypeService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<BodyType>> getAll() {
        List<BodyType> bodyTypeList = bodyTypeService.getAll();

        return commonResponseGenerator.successResponse(bodyTypeList);
    }

    @GetMapping("/{id}")
    public CommonResponse<BodyType> getBodyTypeById(@PathVariable(value = "id") Long bodyTypeId) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeService.getBodyTypeById(bodyTypeId);

        return commonResponseGenerator.successResponse(bodyType);
    }

    @PostMapping("/")
    public CommonResponse<BodyType> createBodyType(@Valid @RequestBody BodyType bodyTypeRequire) {
        BodyType bodyType = bodyTypeService.createBodyType(bodyTypeRequire);

        return commonResponseGenerator.successResponse(bodyType);
    }

    @PutMapping("/{id}")
    public CommonResponse<BodyType> updateBodyType(@PathVariable(value = "id") Long body_type_id, @Valid @RequestBody BodyType bodyTypeDetils) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeService.updateBodyTypeById(body_type_id, bodyTypeDetils);

        return commonResponseGenerator.successResponse(bodyType);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyType(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.deleteBodyTypeById(body_type_id);
    }
}