package com.example.choopo.web.controller;

import com.example.choopo.web.dto.ArticleDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Article;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.ArticleImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/article")
public class AnonymousArticleController {

    @Autowired private ArticleImpl articleService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping
    public CommonResponse<List<ArticleDTO>> getAllArticleAnonymous() throws  ResourceNotFoundExceotion{
        Stream<Object> articleList = articleService.getAnonymousArticle().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("/scramble")
    public CommonResponse<List<ArticleDTO>> findMathRandomAnonymous(){
        Stream<Object> articleList = articleService.getAnonymousScramble().stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("/{id}")
    public CommonResponse<ArticleDTO> findByIdAnonymous(@PathVariable("id") Long articletatusId) throws ResourceNotFoundExceotion {

        Article article = articleService.getAnonymousById(articletatusId);

        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return commonResponseGenerator.successResponse(articleDTO);
    }
}
