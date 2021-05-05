package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/body-type")
public class BodyTypeController {

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    @GetMapping("/")
    public List<BodyType> getAllBodyType(){
        return bodyTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyType> getBodyTypeById(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));
        return ResponseEntity.ok().body(bodyType);
    }

    @PostMapping("/")
    public BodyType createBodyType(@Valid @RequestBody BodyType bodyType) {
        return bodyTypeRepository.save(bodyType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodyType> updateBodyType(@PathVariable(value = "id") Long body_type_id, @Valid @RequestBody BodyType bodyTypeDetails) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND " + body_type_id));

        bodyType.setBody_type_name(bodyTypeDetails.getBody_type_name());
        bodyType.setBody_type_code(bodyTypeDetails.getBody_type_code());
        final BodyType updatedBodyType = bodyTypeRepository.save(bodyType);
        return ResponseEntity.ok(updatedBodyType);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyType(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND " + body_type_id));

        bodyTypeRepository.delete(bodyType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
