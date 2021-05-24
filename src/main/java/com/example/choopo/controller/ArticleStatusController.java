package com.example.choopo.controller;

import com.example.choopo.dto.ArticleStatusDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.ArticleStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/article-status")
public class ArticleStatusController {

    @Autowired private ArticleStatusImpl articleStatusService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<ArticleStatus>> getAll() {

        List<ArticleStatus> articleStatusList = articleStatusService.getAll();

        return commonResponseGenerator.successResponse(articleStatusList);
    }

    @GetMapping("/{id}")
    public CommonResponse<ArticleStatus> getArticleStatusById(@PathVariable(value = "id") Long articleStatusId) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusService.getArticleStatusById(articleStatusId);

        return commonResponseGenerator.successResponse(articleStatus);
    }

    @PostMapping("/")
    public CommonResponse<ArticleStatus> createArticleStatus(@Valid @RequestBody ArticleStatus articleStatusRequire) {
        ArticleStatus articleStatus = articleStatusService.createArticleStatus(articleStatusRequire);

        return commonResponseGenerator.successResponse(articleStatus);
    }

    @PutMapping("/{id}")
    public CommonResponse<ArticleStatus> updateArticleStatusById(@PathVariable(value = "id") Long articleStatusId, @Valid @RequestBody ArticleStatus articleStatusDetils) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusService.updateArticleStatusById(articleStatusId, articleStatusDetils);

        return commonResponseGenerator.successResponse(articleStatus);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticleStatusById(@PathVariable(value = "id") Long articleStatusId) throws ResourceNotFoundExceotion {
        return articleStatusService.deleteArticleStatusById(articleStatusId);
    }
}
