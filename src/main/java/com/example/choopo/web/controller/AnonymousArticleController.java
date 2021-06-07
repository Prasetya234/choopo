package com.example.choopo.web.controller;

import com.example.choopo.web.dto.ArticleDTO;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.ArticleImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/article/anonymous/")
public class AnonymousArticleController {
    @Autowired
    private ArticleImpl articleService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping
    public CommonResponse<List<ArticleDTO>> getAllArticle(){
        Stream<Object> articleList = articleService.getAnonymousArticle("1").stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }

    @GetMapping("scramble")
    public CommonResponse<List<ArticleDTO>> findMathRandom(){
        Stream<Object> articleList = articleService.getAnonymousScramble("1").stream().map(article -> modelMapper.map(article, ArticleDTO.class));
        return commonResponseGenerator.successResponse(articleList);
    }
}
