package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.repository.ArticleRepository;
import com.example.choopo.repository.ArticleStatusRepository;
import com.example.choopo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleImpl implements ArticleService{

    @Autowired private ArticleRepository articleRepository;

    @Autowired private ArticleStatusRepository articleStatusRepository;

    @Autowired private CategoryRepository categoryRepository;


    @Override
    public Article createArticle(Article articleRequire) throws ResourceNotFoundExceotion {
        Article articleStatus = articleStatusRepository.findById(Long.valueOf(articleRequire.getArticleStatus()))
                .map(articleStatus1 -> {
                    articleRequire.setArticleStatusId(articleStatus1);
                    return articleRequire;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
        Article category = categoryRepository.findById(Long.valueOf(articleRequire.getCategory()))
                .map(category1 -> {
                    articleRequire.setCategoryId(category1);
                    return articleStatus;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));
        return articleRepository.save(articleRequire);
    }

    @Override
    public List<Article> getAllArticle(int page, int size) {
        List<Article> tutorials = new ArrayList<Article>();
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Article> articleList = articleRepository.findAll(pageRequest);
        tutorials = articleList.getContent();

        return tutorials;
    }

    @Override
    public List<Article> findLatestNews() {
        return articleRepository.articleLatestNews();
    }

    @Override
    public List<Article> findTopTen() {
        return articleRepository.articleTopTen();
    }

    @Transactional
    @Override
    public List<Article> findMathRandom() {
        return articleRepository.articleScramble();
    }

    @Override
    public Article getArticleById(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND"));
        article.setTotalView(article.getTotalView() + 1);
        final Article totalView = articleRepository.save(article);
        return totalView;
    }

    @Override
    public Article updateArticle(Long articleId, Article articleDetails) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND"));

        article.setSubtitle(articleDetails.getSubtitle());
        article.setTitle(articleDetails.getTitle());
        article.setMainImage(articleDetails.getMainImage());
        article.setTopic(articleDetails.getTopic());
        article.setArticleStatus(articleDetails.getArticleStatus());
        article.setCategory(articleDetails.getCategory());
        Article articleStatus1 = articleStatusRepository.findById(Long.valueOf(articleDetails.getArticleStatus())).map(articleStatus -> {
            articleDetails.setArticleStatusId(articleStatus);
            return articleDetails;
        }).orElseThrow(() ->
                new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
        Article category1 = categoryRepository.findById(Long.valueOf(articleDetails.getCategory())).map(category -> {
            articleDetails.setCategoryId(category);
            return articleStatus1;
        }).orElseThrow(() ->
                new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));
        article.setArticleStatusId(articleStatus1.getArticleStatusId());
        article.setCategoryId(category1.getCategoryId());
        final Article updateData = articleRepository.save(article);

        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteArticle(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND " + articleId));

        articleRepository.delete(article);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }
}
