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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired ArticleRepository articleRepository;

    @Autowired ArticleStatusRepository articleStatusRepository;

    @Autowired CategoryRepository categoryRepository;

    // POST ARTICLE
    public Article createArticle(@Valid @RequestBody Article articleRequest) throws ResourceNotFoundExceotion {
        Article articleStatus1 = articleStatusRepository.findById(articleRequest.getArticleStatus().getArticleStatusId())
                .map(articleStatus -> {
                    articleRequest.setArticleStatus(articleStatus);
                    return articleRequest;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
        Article category1 = categoryRepository.findById(articleRequest.getCategory().getCategoryId())
                .map(category -> {
                    articleRequest.setCategory(category);
                    return articleStatus1;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));
        return articleRepository.save(articleRequest);

    }

    // GET ALL ARTICLE
    public ResponseEntity<Map<String, Object>> getAllArticle(int page,int size) {
        try {

            List<Article> tutorials = new ArrayList<Article>();
            PageRequest pageRequest = PageRequest.of(page, size);

            Page<Article> pageTuts = articleRepository.findAll(pageRequest);
            tutorials = pageTuts.getContent();


            Map<String, Object> response = new HashMap<>();
            response.put("status","SUCCESS");
            response.put("message","SUCCESS");
            response.put("currentPage", pageTuts.getNumber());
            response.put("size",pageTuts.getSize());
            response.put("totalElement", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("content", tutorials);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("ERROR", HttpStatus.BAD_REQUEST);
        }
    }

    // GET ARTICLE TOP NEWS
    public ResponseEntity<Map<String, Object>> findLatestNews(){
        try {
            List<Article> articles = new ArrayList<>();

            articles = articleRepository.articleLatestNews();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articles);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ARTICLE TOP 10
    public ResponseEntity<Map<String, Object>> findTopTen() {
        try {
            List<Article> articles = new ArrayList<>();

            articles = articleRepository.articleTopTen();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articles);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ARTICLE SCRAMBLE
    public ResponseEntity<Map<String, Object>> findMathRandom(){
        try {

            List<Article> articles = new ArrayList<>();

            articles = articleRepository.articleScramble();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articles);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ARTICLE BY ID
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        Article article  = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND"));
        article.setTotalView(article.getTotalView() + 1);
        final Article updateArticle = articleRepository.save(article);

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", updateArticle);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE ARTICLE BY ID
    public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long article_id, Article articleDetails) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND" + article_id));

        article.setSubtitle(articleDetails.getSubtitle());
        article.setTitle(articleDetails.getTitle());
        article.setMainImage(articleDetails.getMainImage());
        article.setTopic(articleDetails.getTopic());
        final Article updateArticle = articleRepository.save(article);
        return ResponseEntity.ok(updateArticle);
    }

    // DELETE ARTICLE BY ID
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND " + article_id));

        articleRepository.delete(article);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }



}
