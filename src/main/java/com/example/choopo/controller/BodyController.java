package com.example.choopo.controller;

import com.example.choopo.dto.BodyDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Body;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.BodyImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/body")
public class BodyController {

    @Autowired private BodyImpl bodyService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<BodyDTO> getAll() {
        Stream<Object> bodyList = bodyService.getAll().stream().map(body -> modelMapper.map(body, BodyDTO.class));

        return commonResponseGenerator.successResponse(bodyList);
    }

    @GetMapping("/{id}")
    public CommonResponse<BodyDTO> getBodyById(@PathVariable(value = "id") Long bodyId) throws ResourceNotFoundExceotion {
        Body body = bodyService.getBodyById(bodyId);

        BodyDTO bodyDTO = modelMapper.map(body, BodyDTO.class);

        return commonResponseGenerator.successResponse(bodyDTO);
    }

    @GetMapping("/findBodyByArticle/{article_id}")
    public CommonResponse<List<BodyDTO>> getBodyByArticleId(@PathVariable(value = "article_id") String articleId) throws ResourceNotFoundExceotion{
        List<Body> body = bodyService.getBodyByArticleId(articleId);

        BodyDTO bodyDTO = modelMapper.map(body, BodyDTO.class);

        return commonResponseGenerator.successResponse(bodyDTO);
    }

    @PostMapping("/")
    public CommonResponse<Body> createBody(@Valid @RequestBody Body bodyDTORequest) throws ResourceNotFoundExceotion {
        Body bodyRequire = modelMapper.map(bodyDTORequest, Body.class);
        Body body = bodyService.createBody(bodyRequire);

        BodyDTO bodyDTO = modelMapper.map(body, BodyDTO.class);
        return commonResponseGenerator.successResponse(bodyDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<Body> updateBodyById(@PathVariable(value = "id") Long bodyId, @Valid @RequestBody Body bodyDTODetails) throws ResourceNotFoundExceotion {
        Body bodyDetails = modelMapper.map(bodyDTODetails, Body.class);
        Body body = bodyService.updateBodyById(bodyId, bodyDetails);

        BodyDTO bodyDTO = modelMapper.map(body, BodyDTO.class);
        return commonResponseGenerator.successResponse(bodyDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBodyById(@PathVariable(value = "id") Long bodyId) throws ResourceNotFoundExceotion {
        return bodyService.deleteBodyById(bodyId);
    }
}