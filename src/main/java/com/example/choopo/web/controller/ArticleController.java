package com.example.choopo.web.controller;

import com.example.choopo.web.dto.ArticleDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Article;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.ArticleImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;
import java.util.stream.Stream;

@RestController
public class ArticleController {

    @Autowired private ArticleImpl articleService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
    @RequestMapping(value = "/admin/article/top-news", method = RequestMethod.GET)
    public CommonResponse<List<ArticleDTO>> findLatestNews(){
        Stream<Object> articleList = articleService.findLatestNews().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
    @RequestMapping(value = "/admin/article/top-10", method = RequestMethod.GET)
    public CommonResponse<List<ArticleDTO>> findTopTen() {
        Stream<Object> articleList = articleService.findTopTen().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
    @RequestMapping(value = "/admin/article/scramble", method = RequestMethod.GET)
    public CommonResponse<List<ArticleDTO>> findMathRandom(){
        Stream<Object> articleList = articleService.findMathRandom().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
    @RequestMapping(value = "/admin/article/", method = RequestMethod.GET)
    public CommonResponse<List<ArticleDTO>> getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Stream<Object> articleList = articleService.getAllArticle(page, size).stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
    @RequestMapping(value = "/admin/article/{id}", method = RequestMethod.GET)
    public CommonResponse<ArticleDTO> getArticleById(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.getArticleById(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return commonResponseGenerator.successResponse(articleDTO);
    }

    // WRITER AREA -----------------------------------

    @PreAuthorize("hasAnyAuthority('WRITER')")
    @RequestMapping(value = "/admin/article/", method = RequestMethod.POST)
    public CommonResponse<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO articleDTORequest) throws ResourceNotFoundExceotion {
        Article articleRequest = modelMapper.map(articleDTORequest, Article.class);

        Article article = articleService.createArticle(articleRequest);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PreAuthorize("hasAnyAuthority('WRITER')")
    @RequestMapping(value = "/admin/article/{id}", method = RequestMethod.PUT)
    public CommonResponse<ArticleDTO> updateArticle(@PathVariable(value = "id") Long articleId, @Valid @RequestBody ArticleDTO articleDTODetails) throws ResourceNotFoundExceotion {
        Article articleDetails = modelMapper.map(articleDTODetails, Article.class);

        Article article = articleService.updateArticle(articleId,articleDetails);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PreAuthorize("hasAnyAuthority('WRITER')")
    @RequestMapping(value = "/admin/nonactive-article/{id}", method = RequestMethod.PUT)
    public CommonResponse<Article> deleteArticle(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {

        Article article = articleService.deleteArticle(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PreAuthorize("hasAnyAuthority('WRITER')")
    @RequestMapping(value = "/admin/nonactive-article/reactive/{id}", method = RequestMethod.PUT)
    public CommonResponse<ArticleDTO> updateActive(@PathVariable("id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.getAnonymousActiveAgain(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }

    // Admin AREA  -----------------------------------

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/admin/article/authorized/{id}", method = RequestMethod.PUT)
    public CommonResponse<ArticleDTO> updatePublikasi(@PathVariable("id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.getArticlePublikasi(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/admin/takedown-article/{id}", method = RequestMethod.PUT)
    public CommonResponse<Article> deleteArticleTakedown(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.deleteArticleTakedown(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/admin/takedown-article/reactive/{id}", method = RequestMethod.PUT)
    public CommonResponse<ArticleDTO> updateTakedownFalse(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleService.takedownReactivate(articleId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }
}