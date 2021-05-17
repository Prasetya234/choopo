package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.repository.BodyRepository;
import com.example.choopo.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static com.example.choopo.response.ResponseBody.resourceBody;

@RestController
@RequestMapping("/body")
public class BodyController {
    @Autowired
    private BodyRepository bodyRepository;
    @Autowired
    private BodyTypeRepository bodyTypeRepository;

//    @GetMapping("/")
//    public List<Body> getAllBody(){
//        return bodyRepository.findAll();
//    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Body> bodies = new ArrayList<>();

            bodies = bodyRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", bodies);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Body> getBodyById(@PathVariable(value = "id") Long body_id) throws ResourceNotFoundExceotion {
//        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND"));
//        return ResponseEntity.ok().body(body);
        Optional<Body> body = Optional.ofNullable(bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", body);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("BODY ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBodyByArticle/{article_id}")
    public List<Body> byArticleId(@PathVariable(value = "article_id") String article_id) throws ResourceNotFoundExceotion{
        return bodyRepository.findByArticleId(article_id);
    }

//    @PostMapping("/")
//    public Body createBody(@Valid @RequestBody Body body) {
//        return  bodyRepository.save(body);
//    }
    @PostMapping("/{id}/type")
    public Body createBody(
            @PathVariable(value = "id") Long body_type_id,
            @Valid @RequestBody Body bodyRequest
    ) throws ResourceNotFoundExceotion {

        return bodyTypeRepository.findById(body_type_id).map(bodyType -> {
            bodyRequest.setBodyType(bodyType);
            return bodyRepository.save(bodyRequest);
        }).orElseThrow(() -> new ResourceNotFoundExceotion("BODY TYPE ID NOT FOUND"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Body> updateBody(@PathVariable(value = "id") Long body_id, @Valid @RequestBody Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

        body.setBodyType(bodyDetails.getBodyType());
        body.setBodyContent(bodyDetails.getBodyContent());
        final Body updatedBody = bodyRepository.save(body);
        return ResponseEntity.ok(updatedBody);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBody(@PathVariable(value = "id") Long body_id) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

        bodyRepository.delete(body);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}