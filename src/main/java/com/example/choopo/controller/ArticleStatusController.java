package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.model.Body;
import com.example.choopo.model.UserType;
import com.example.choopo.repository.ArticleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reference/article-status")
public class ArticleStatusController {

    @Autowired
    private ArticleStatusRepository articleStatusRepository;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<ArticleStatus> articleStatuses = new ArrayList<>();

            articleStatuses = articleStatusRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articleStatuses);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleStatus> getArticleStatusById(@PathVariable(value = "id") Long article_status_id) throws ResourceNotFoundExceotion {
//        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
//        return ResponseEntity.ok().body(articleStatus);
        Optional<ArticleStatus> articleStatus = Optional.ofNullable(articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", articleStatus);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ArticleStatus createArticleStatus(@Valid @RequestBody ArticleStatus articleStatus) {
        return articleStatusRepository.save(articleStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleStatus> updateArticleStatus(@PathVariable(value = "id") Long article_status_id, @Valid @RequestBody ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND " + article_status_id));

        articleStatus.setArticleStatusName(articleStatusDetails.getArticleStatusName());
        articleStatus.setArticleStatusCode(articleStatusDetails.getArticleStatusCode());
        final ArticleStatus updateArticleStatus = articleStatusRepository.save(articleStatus);
        return ResponseEntity.ok(updateArticleStatus);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteArticleStatus(@PathVariable(value = "id") Long article_status_id) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND " + article_status_id));

        articleStatusRepository.delete(articleStatus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
