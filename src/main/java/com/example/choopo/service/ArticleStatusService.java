package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.repository.ArticleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleStatusService {

    @Autowired private ArticleStatusRepository articleStatusRepository;

    // POST ARTICLE STATUS
    public ArticleStatus createArticleStatus(ArticleStatus articleStatus) {

        articleStatus = articleStatusRepository.save(articleStatus);

        return articleStatus;
    }

    // GET ALL ARTICLE STATUS
    public ResponseEntity<Map<String, Object>> getAll() {
            List<ArticleStatus> articleStatuses = new ArrayList<>();

            articleStatuses = articleStatusRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articleStatuses);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET ARTICLE STATUS BY ID
    public ResponseEntity<ArticleStatus> getArticleStatusById(Long article_status_id) throws ResourceNotFoundExceotion {
        Optional<ArticleStatus> articleStatus = Optional.ofNullable(articleStatusRepository.findById(article_status_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articleStatus);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // UPDATE BODY ARTICLE STATUS
    public ResponseEntity<ArticleStatus> updateArticleStatus( Long article_status_id, ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND " + article_status_id));

        articleStatus.setArticleStatusName(articleStatusDetails.getArticleStatusName());
        articleStatus.setArticleStatusCode(articleStatusDetails.getArticleStatusCode());
        final ArticleStatus updateArticleStatus = articleStatusRepository.save(articleStatus);

        return ResponseEntity.ok(updateArticleStatus);
    }

    // DELETE ARTICLE STATUS BY ID
    public Map<String, Boolean> deleteArticleStatus( Long article_status_id) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND " + article_status_id));

        articleStatusRepository.delete(articleStatus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
