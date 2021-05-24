package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.BodyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/body")
public class BodyController {

    @Autowired private BodyImpl bodyService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<Body>> getAll() {
        List<Body> bodyList = bodyService.getAll();

        return commonResponseGenerator.successResponse(bodyList);
    }

    @GetMapping("/{id}")
    public CommonResponse<Body> getBodyById(@PathVariable(value = "id") Long bodyId) throws ResourceNotFoundExceotion {
        Body body = bodyService.getBodyById(bodyId);

        return commonResponseGenerator.successResponse(body);
    }

    @GetMapping("/findBodyByArticle/{article_id}")
    public CommonResponse<List<Body>> getBodyByArticleId(@PathVariable(value = "article_id") String articleId) throws ResourceNotFoundExceotion{
        List<Body> bodyList = bodyService.getBodyByArticleId(articleId);

        return commonResponseGenerator.successResponse(bodyList);
    }

    @PostMapping("/")
    public CommonResponse<Body> createBody(@Valid @RequestBody Body bodyRequest) throws ResourceNotFoundExceotion {
        Body body = bodyService.createBody(bodyRequest);

        return commonResponseGenerator.successResponse(body);
    }

    @PutMapping("/{id}")
    public CommonResponse<Body> updateBodyById(@PathVariable(value = "id") Long bodyId, @Valid @RequestBody Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyService.updateBodyById(bodyId, bodyDetails);

        return commonResponseGenerator.successResponse(body);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyById(@PathVariable(value = "id") Long bodyId) throws ResourceNotFoundExceotion {
        return bodyService.deleteBodyById(bodyId);
    }
}