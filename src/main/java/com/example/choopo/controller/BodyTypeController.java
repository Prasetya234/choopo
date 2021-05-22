package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.service.BodyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/body-type")
public class BodyTypeController {

    @Autowired private BodyTypeService bodyTypeService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return bodyTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyType> getBodyById(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.getBodyTypeById(body_type_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodyType> updateBodyType(@PathVariable(value = "id") Long body_type_id, @Valid @RequestBody BodyType bodyTypeDetails) throws ResourceNotFoundExceotion {
        return bodyTypeService.updateBodyType(body_type_id, bodyTypeDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyType(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.deleteBodyType(body_type_id);
    }
}