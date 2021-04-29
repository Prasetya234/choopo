package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/article")
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
        return ResponseEntity.ok().body(article);
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
        article.setTotal_view(articleDetails.getTotal_view());
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