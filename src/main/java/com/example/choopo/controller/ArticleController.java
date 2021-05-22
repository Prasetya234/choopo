package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired private ArticleService articleService;

    @GetMapping("/top-news")
    public ResponseEntity<Map<String, Object>> findLatestNews(){
        return articleService.findLatestNews();
    }

    @GetMapping("/top-10")
    public ResponseEntity<Map<String, Object>> findTopTen() {
        return articleService.findTopTen();
    }

    @GetMapping("/scramble")
    public ResponseEntity<Map<String, Object>> findMathRandom(){
        return articleService.findMathRandom();
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllArticle(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        return articleService.getAllArticle(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        return articleService.getArticleById(article_id);
    }

    @PostMapping("/")
    public Article createArticle(@Valid @RequestBody Article articleRequest) throws ResourceNotFoundExceotion {
       return articleService.createArticle(articleRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long article_id, @Valid @RequestBody Article articleDetails) throws ResourceNotFoundExceotion {
        return articleService.updateArticle(article_id,articleDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        return articleService.deleteArticle(article_id);
    }
}