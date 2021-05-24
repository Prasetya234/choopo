package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.service.BodyTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/body-type")
public class BodyTypeController {

    @Autowired private BodyTypeImpl bodyTypeService;

    @GetMapping("/")
    public List<BodyType> getAll() {
        return bodyTypeService.getAll();
    }

    @GetMapping("/{id}")
    public BodyType getBodyTypeById(@PathVariable(value = "id") Long bodyTypeId) throws ResourceNotFoundExceotion {
        return bodyTypeService.getBodyTypeById(bodyTypeId);
    }

    @PostMapping("/")
    public BodyType createBodyType(@Valid @RequestBody BodyType bodyTypeRequire) {
        return bodyTypeService.createBodyType(bodyTypeRequire);
    }

    @PutMapping("/{id}")
    public BodyType updateBodyType(@PathVariable(value = "id") Long body_type_id, @Valid @RequestBody BodyType bodyTypeDetils) throws ResourceNotFoundExceotion {
        return bodyTypeService.updateBodyTypeById(body_type_id, bodyTypeDetils);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyType(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.deleteBodyTypeById(body_type_id);
    }
}