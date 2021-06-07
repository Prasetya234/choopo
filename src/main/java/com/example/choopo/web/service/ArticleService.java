package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    Article createArticle(Article articleRequire) throws ResourceNotFoundExceotion;

    List<Article> getAllArticle(int page, int size);

    List<Article> findLatestNews();

    List<Article> findTopTen();

    List<Article> findMathRandom();

    Article getArticleById(Long articleId) throws ResourceNotFoundExceotion;

    Article updateArticle(Long articleId, Article articleDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteArticle(Long articleId) throws ResourceNotFoundExceotion;

    List<Article> getAnonymousArticle(String status);

    List<Article> getAnonymousScramble(String status);
}
