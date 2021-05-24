package com.example.choopo.controller;

import com.example.choopo.dto.BodyDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/body")
public class BodyController {

    @Autowired private BodyService bodyService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return bodyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Body> getBodyById(@PathVariable(value = "id") Long body_id) throws ResourceNotFoundExceotion {
        return bodyService.getBodyById(body_id);
    }

    @GetMapping("/findBodyByArticle/{article_id}")
    public Body byArticleId(@PathVariable(value = "article_id") String article_id) throws ResourceNotFoundExceotion{
        return (Body) bodyService.byArticleId(article_id);
    }

    @PostMapping("/")
    public Body createBody(@Valid @RequestBody Body bodyRequest) throws ResourceNotFoundExceotion {
        return bodyService.createBody(bodyRequest);
    }

    @PutMapping("/{id}")
    public Body updateBody(@PathVariable(value = "id") Long body_id, @Valid @RequestBody Body bodyDetails) throws ResourceNotFoundExceotion {
       return bodyService.updateBody(body_id, bodyDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBody(@PathVariable(value = "id") Long body_id) throws ResourceNotFoundExceotion {
       return bodyService.deleteBody(body_id);
    }
}