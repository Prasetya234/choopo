package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/top-ten")
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


    @GetMapping("/scramble")
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

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> findByTitle(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
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

    @PostMapping("/")
    public Article createArticle(@Valid @RequestBody Article article) {
        return  articleRepository.save(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long article_id, @Valid @RequestBody Article articleDetails) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND" + article_id));

        article.setArticleStatus(articleDetails.getArticleStatus());
        article.setCategoryId(articleDetails.getCategoryId());
        article.setSubtitle(articleDetails.getSubtitle());
        article.setTitle(articleDetails.getTitle());
        article.setMainImage(articleDetails.getMainImage())   ;
        article.setTopic(articleDetails.getTopic());
        final Article updateArticle = articleRepository.save(article);
        return ResponseEntity.ok(updateArticle);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long article_id) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(article_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND " + article_id));

        articleRepository.delete(article);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}