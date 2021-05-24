package com.example.choopo.controller;

import com.example.choopo.dto.ArticleStatusDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.service.ArticleStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/article-status")
public class ArticleStatusController {

    @Autowired private ArticleStatusImpl articleStatusService;

    @GetMapping("/")
    public List<ArticleStatus> getAll() {
        return articleStatusService.getAll();
    }

    @GetMapping("/{id}")
    public ArticleStatus getArticleStatusById(@PathVariable(value = "id") Long articleStatusId) throws ResourceNotFoundExceotion {
        return articleStatusService.getArticleStatusById(articleStatusId);
    }

    @PostMapping("/")
    public ArticleStatus createArticleStatus(@Valid @RequestBody ArticleStatus articleStatus) {
        return articleStatusService.createArticleStatus(articleStatus);
    }

    @PutMapping("/{id}")
    public ArticleStatus updateArticleStatusById(@PathVariable(value = "id") Long articleStatusId, @Valid @RequestBody ArticleStatus articleStatusDetils) throws ResourceNotFoundExceotion {
        return articleStatusService.updateArticleStatusById(articleStatusId, articleStatusDetils);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticleStatusById(@PathVariable(value = "id") Long articleStatusId) throws ResourceNotFoundExceotion {
        return articleStatusService.deleteArticleStatusById(articleStatusId);
    }
}
