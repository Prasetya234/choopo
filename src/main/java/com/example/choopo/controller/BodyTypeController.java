package com.example.choopo.controller;

import com.example.choopo.dto.BodyTypeDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
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
    public ResponseEntity<BodyTypeDTO> getBodyTypeById(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.getBodyTypeById(body_type_id);
    }

    @PostMapping("/")
    public BodyTypeDTO createBodyType(@Valid @RequestBody BodyTypeDTO bodyTypeDTO) {
        return bodyTypeService.createBodyType(bodyTypeDTO);
    }

    @PutMapping("/{id}")
    public BodyTypeDTO updateBodyType(@PathVariable(value = "id") Long body_type_id, @Valid @RequestBody BodyTypeDTO bodyTypeDTODetils) throws ResourceNotFoundExceotion {
        return bodyTypeService.updateBodyType(body_type_id, bodyTypeDTODetils);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyType(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        return bodyTypeService.deleteBodyType(body_type_id);
    }
}