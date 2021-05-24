package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.service.ArticleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired private ArticleImpl articleService;

    @GetMapping("/top-news")
    public List<Article> findLatestNews(){
        return articleService.findLatestNews();
    }

    @GetMapping("/top-10")
    public List<Article> findTopTen() {
        return articleService.findTopTen();
    }

    @GetMapping("/scramble")
    public List<Article> findMathRandom(){
        return articleService.findMathRandom();
    }

    @GetMapping("/")
    public List<Article> getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return articleService.getAllArticle(page, size);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        return articleService.getArticleById(articleId);
    }

    @PostMapping("/")
    public Article createArticle(@Valid @RequestBody Article articleRequest) throws ResourceNotFoundExceotion {
       return articleService.createArticle(articleRequest);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable(value = "id") Long articleId, @Valid @RequestBody Article articleDetails) throws ResourceNotFoundExceotion {
        return articleService.updateArticle(articleId,articleDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        return articleService.deleteArticle(articleId);
    }
}