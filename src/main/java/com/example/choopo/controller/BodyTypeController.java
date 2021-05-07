package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.BodyType;
import com.example.choopo.model.Category;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/body-type")
public class BodyTypeController {

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    //    @GetMapping("/")
//    public List<BodyType> getAllBodyType(){
//        return bodyTypeRepository.findAll();
//    }
    @GetMapping("/")
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

    @GetMapping("/{id}")
    public ResponseEntity<BodyType> getBodyTypeById(@PathVariable(value = "id") Long body_type_id) throws ResourceNotFoundExceotion {
//        BodyType bodyType = bodyTypeRepository.findById(body_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));
//        return ResponseEntity.ok().body(bodyType);
        Optional<BodyType> bodyType = Optional.ofNullable(bodyTypeRepository.findById(body_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND")));

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

    @PostMapping("/")
    public BodyType createBodyType(@Valid @RequestBody BodyType bodyType) {
        return bodyTypeRepository.save(bodyType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodyType> updateBodyType(@PathVariable(value = "id") Long body_type_id, @Valid @RequestBody BodyType bodyTypeDetails) throws ResourceNotFoundExceotion {
        BodyType bodyType = bodyTypeRepository.findById(body_type_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND " + body_type_id));

        bodyType.setBodyTypeName(bodyTypeDetails.getBodyTypeName());
        bodyType.setBodyTypeCode(bodyTypeDetails.getBodyTypeCode());
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