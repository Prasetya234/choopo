package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.service.ArticleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/article-status")
public class ArticleStatusController {

    @Autowired private ArticleStatusService articleStatusService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return articleStatusService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleStatus> getArticleStatusById(@PathVariable(value = "id") Long article_status_id) throws ResourceNotFoundExceotion {
        return articleStatusService.getArticleStatusById(article_status_id);
    }

   @PostMapping("/")
   public ArticleStatus createArticleStatus(@Valid @RequestBody ArticleStatus articleStatus) {
       return articleStatusService.createArticleStatus(articleStatus);
   }


    @PutMapping("/{id}")
    public ResponseEntity<ArticleStatus> updateArticleStatus(@PathVariable(value = "id") Long article_status_id, @Valid @RequestBody ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion {
//        articleStatusDetails.setArticleStatusName(articleStatusDetails.getArticleStatusName());
//        articleStatusDetails.setArticleStatusCode(articleStatusDetails.getArticleStatusCode());
        return articleStatusService.updateArticleStatus(article_status_id, articleStatusDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticleStatus(@PathVariable(value = "id") Long article_status_id) throws ResourceNotFoundExceotion {
        return articleStatusService.deleteArticleStatus(article_status_id);
    }
}
