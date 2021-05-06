package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.BodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/body")
public class BodyController {
    @Autowired
    private BodyRepository bodyRepository;

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
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND"));
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/findBodyByArticle/{article_id}")
     public List<Body> byArticleId(@PathVariable(value = "article_id") String article_id) throws ResourceNotFoundExceotion{
        return bodyRepository.findByArticleId(article_id);
    }

    @PostMapping("/")
    public Body createBody(@Valid @RequestBody Body body) {
        return  bodyRepository.save(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Body> updateBody(@PathVariable(value = "id") Long body_id, @Valid @RequestBody Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

        body.setBody_type(bodyDetails.getBody_type());
        body.setBody_content(bodyDetails.getBody_content());
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
