package com.example.choopo.web.controller;

import com.example.choopo.web.dto.ArticleStatusDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.ArticleStatus;
import com.example.choopo.web.service.response.CommonResponse;
import com.example.choopo.web.service.response.CommonResponseGenerator;
import com.example.choopo.web.service.ArticleStatusImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/reference/article-status")
public class ArticleStatusController {

    @Autowired private ArticleStatusImpl articleStatusService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<ArticleStatusDTO>> getAll() {

        Stream<Object> articleStatusList = articleStatusService.getAll().stream().map(articleStatus -> modelMapper.map(articleStatus, ArticleStatusDTO.class));

        return commonResponseGenerator.successResponse(articleStatusList);
    }

    @GetMapping("/{id}")
    public CommonResponse<ArticleStatusDTO> getArticleStatusById(@PathVariable(value = "id") Long articleStatusId) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusService.getArticleStatusById(articleStatusId);

        // CONVERT ENTITY TO DTO
        ArticleStatusDTO articleStatusDTO = modelMapper.map(articleStatus, ArticleStatusDTO.class);

        return commonResponseGenerator.successResponse(articleStatusDTO);
    }

    @PostMapping("/")
    public CommonResponse<ArticleStatusDTO> createArticleStatus(@Valid @RequestBody ArticleStatusDTO articleStatusDTORequire) {

        // CONVERT DTO TO ENTITY
        ArticleStatus articleStatusRequire = modelMapper.map(articleStatusDTORequire, ArticleStatus.class);

        ArticleStatus articleStatus = articleStatusService.createArticleStatus(articleStatusRequire);

        // CONVERT ENTITY TO DTO
        ArticleStatusDTO articleStatusDTO = modelMapper.map(articleStatus, ArticleStatusDTO.class);

        return commonResponseGenerator.successResponse(articleStatusDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<ArticleStatusDTO> updateArticleStatusById(@PathVariable(value = "id") Long articleStatusId, @Valid @RequestBody ArticleStatusDTO articleStatusDTODetils) throws ResourceNotFoundExceotion {

        // CONVERT DTO TO ENTITY
        ArticleStatus articleStatusDetils = modelMapper.map(articleStatusDTODetils, ArticleStatus.class);

        ArticleStatus articleStatus = articleStatusService.updateArticleStatusById(articleStatusId, articleStatusDetils);

        // CONVERT ENTITY TO DTO
        ArticleStatusDTO articleStatusDTO = modelMapper.map(articleStatus, ArticleStatusDTO.class);
        return commonResponseGenerator.successResponse(articleStatusDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticleStatusById(@PathVariable(value = "id") Long articleStatusId) throws ResourceNotFoundExceotion {
        return articleStatusService.deleteArticleStatusById(articleStatusId);
    }
}
