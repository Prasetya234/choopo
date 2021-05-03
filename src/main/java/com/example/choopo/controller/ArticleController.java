package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        Article article  = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE NOT FOUND"));
        article.setTotal_view(article.getTotal_view() + 1);
        final Article updateArticle = articleRepository.save(article);
        return ResponseEntity.ok().body(updateArticle);
    }

    @PostMapping("/")
    public Article createArticle(@Valid @RequestBody Article article) {
        return  articleRepository.save(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long article_id, @Valid @RequestBody Article articleDetails) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE NOT FOUND" + article_id));

        article.setArticle_status(articleDetails.getArticle_status());
        article.setCategory_id(articleDetails.getCategory_id());
        article.setSubtitle(articleDetails.getSubtitle());
        article.setTitle(articleDetails.getTitle());
        article.setTopic(articleDetails.getTopic());
        final Article updateArticle = articleRepository.save(article);
        return ResponseEntity.ok(updateArticle);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE NOT FOUND " + article_id));

        articleRepository.delete(article);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
