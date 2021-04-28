package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.repository.ArticleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reference/article-status")
public class ArticleStatusController {

    @Autowired
    private ArticleStatusRepository articleStatusRepository;

    @GetMapping("/")
    public List<ArticleStatus> getAllEmployees(){
        return articleStatusRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleStatus> getArticleStatusById(@PathVariable(value = "id") Long article_status_id) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS NOT FOUND"));
        return ResponseEntity.ok().body(articleStatus);
    }

    @PostMapping("/")
    public ArticleStatus createArticleStatus(@Valid @RequestBody ArticleStatus articleStatus) {
        return articleStatusRepository.save(articleStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleStatus> updateArticleStatus(@PathVariable(value = "id") Long article_status_id, @Valid @RequestBody ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS NOT FOUND" + article_status_id));

        articleStatus.setArticle_status_name(articleStatusDetails.getArticle_status_name());
        articleStatus.setArticle_status_code(articleStatusDetails.getArticle_status_code());
        final ArticleStatus updateArticleStatus = articleStatusRepository.save(articleStatus);
        return ResponseEntity.ok(updateArticleStatus);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticleStatus(@PathVariable(value = "id") Long article_status_id) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS NOT FOUND " + article_status_id));

        articleStatusRepository.delete(articleStatus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
