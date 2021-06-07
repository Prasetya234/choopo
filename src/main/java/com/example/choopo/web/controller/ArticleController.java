package com.example.choopo.web.controller;

import com.example.choopo.web.dto.ArticleDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Article;
import com.example.choopo.web.service.response.CommonResponse;
import com.example.choopo.web.service.response.CommonResponseGenerator;
import com.example.choopo.web.service.ArticleImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/article/")
public class ArticleController {

    @Autowired private ArticleImpl articleService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("top-news")
    public CommonResponse<List<ArticleDTO>> findLatestNews(){
        Stream<Object> articleList = articleService.findLatestNews().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("top-10")
    public CommonResponse<List<ArticleDTO>> findTopTen() {
        Stream<Object> articleList = articleService.findTopTen().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("scramble")
    public CommonResponse<List<ArticleDTO>> findMathRandom(){
        Stream<Object> articleList = articleService.findMathRandom().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping
    public CommonResponse<List<ArticleDTO>> getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Stream<Object> articleList = articleService.getAllArticle(page, size).stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("{id}")
    public CommonResponse<ArticleDTO> getArticleById(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.getArticleById(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PostMapping
    public CommonResponse<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO articleDTORequest) throws ResourceNotFoundExceotion {
        Article articleRequest = modelMapper.map(articleDTORequest, Article.class);

        Article article = articleService.createArticle(articleRequest);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PutMapping("{id}")
    public CommonResponse<ArticleDTO> updateArticle(@PathVariable(value = "id") Long articleId, @Valid @RequestBody ArticleDTO articleDTODetails) throws ResourceNotFoundExceotion {
        Article articleDetails = modelMapper.map(articleDTODetails, Article.class);

        Article article = articleService.updateArticle(articleId,articleDetails);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        return articleService.deleteArticle(articleId);
    }
}