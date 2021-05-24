package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.service.BodyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/body")
public class BodyController {

    @Autowired private BodyImpl bodyService;

    @GetMapping("/")
    public List<Body> getAll() {
        return bodyService.getAll();
    }

    @GetMapping("/{id}")
    public Body getBodyById(@PathVariable(value = "id") Long bodyId) throws ResourceNotFoundExceotion {
        return bodyService.getBodyById(bodyId);
    }

    @GetMapping("/findBodyByArticle/{article_id}")
    public List<Body> getBodyByArticleId(@PathVariable(value = "article_id") String articleId) throws ResourceNotFoundExceotion{
        return bodyService.getBodyByArticleId(articleId);
    }

    @PostMapping("/")
    public Body createBody(@Valid @RequestBody Body bodyRequest) throws ResourceNotFoundExceotion {
        return bodyService.createBody(bodyRequest);
    }

    @PutMapping("/{id}")
    public Body updateBodyById(@PathVariable(value = "id") Long bodyId, @Valid @RequestBody Body bodyDetails) throws ResourceNotFoundExceotion {
        return bodyService.updateBodyById(bodyId, bodyDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyById(@PathVariable(value = "id") Long bodyId) throws ResourceNotFoundExceotion {
        return bodyService.deleteBodyById(bodyId);
    }
}