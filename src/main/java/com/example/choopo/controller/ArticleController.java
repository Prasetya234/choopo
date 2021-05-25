package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
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

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/top-news")
    public CommonResponse<List<Article>> findLatestNews(){
        List<Article> articleList = articleService.findLatestNews();
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("/top-10")
    public CommonResponse<List<Article>> findTopTen() {
        List<Article> articleList = articleService.findTopTen();
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("/scramble")
    public CommonResponse<List<Article>> findMathRandom(){
        List<Article> articleList = articleService.findMathRandom();
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("/")
    public CommonResponse<List<Article>> getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        List<Article> articleList = articleService.getAllArticle(page, size);
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("/{id}")
    public CommonResponse<Article> getArticleById(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.getArticleById(articleId);
        return commonResponseGenerator.successResponse(article);
    }

    @PostMapping("/")
    public CommonResponse<Article> createArticle(@Valid @RequestBody Article articleRequest) throws ResourceNotFoundExceotion {
       Article article = articleService.createArticle(articleRequest);

        return commonResponseGenerator.successResponse(article);
    }

    @PutMapping("/{id}")
    public CommonResponse<Article> updateArticle(@PathVariable(value = "id") Long articleId, @Valid @RequestBody Article articleDetails) throws ResourceNotFoundExceotion {
        Article article = articleService.updateArticle(articleId,articleDetails);

        return commonResponseGenerator.successResponse(article);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        return articleService.deleteArticle(articleId);
    }
}