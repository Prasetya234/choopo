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

    Article deleteArticle(Long articleId) throws ResourceNotFoundExceotion;

    Article getArticlePublikasi(Long articleId) throws ResourceNotFoundExceotion;

    Article deleteArticleTakedown(Long articleId) throws ResourceNotFoundExceotion;

    Article takedownReactivate(Long articleId) throws ResourceNotFoundExceotion;

    //   ANONYMOUS AREA  -----

    List<Article> getAnonymousArticle() throws ResourceNotFoundExceotion;

    List<Article> getAnonymousScramble();

    Article getAnonymousById(Long articleId) throws ResourceNotFoundExceotion;

    Article getAnonymousActiveAgain(Long articleId) throws ResourceNotFoundExceotion;
}
