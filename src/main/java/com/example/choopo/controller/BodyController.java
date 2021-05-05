package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Article;
import com.example.choopo.model.Body;
import com.example.choopo.repository.ArticleRepository;
import com.example.choopo.repository.BodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/body")
public class BodyController {
    @Autowired
    private BodyRepository bodyRepository;
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public List<Body> getAllBody(){
        return bodyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Body> getBodyById(@PathVariable(value = "id") Long body_id) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND"));
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/findBodyByArticle/{article_id}")
     public ResponseEntity<Body> byArticleId(@PathVariable(value = "article_id") String article_id) throws ResourceNotFoundExceotion{
        Body body = bodyRepository.findByArticleId(article_id);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/")
    public Body createBody(@Valid @RequestBody Body body) {
        return  bodyRepository.save(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Body> updateBody(@PathVariable(value = "id") Long body_id, @Valid @RequestBody Body bodyDetails) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

        body.setBody_type(bodyDetails.getBody_type());
        body.setBody_content(bodyDetails.getBody_content());
        final Body updatedBody = bodyRepository.save(body);
        return ResponseEntity.ok(updatedBody);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBody(@PathVariable(value = "id") Long body_id) throws ResourceNotFoundExceotion {
        Body body = bodyRepository.findById(body_id).orElseThrow(() -> new ResourceNotFoundExceotion("BODY ID NOT FOUND " + body_id));

        bodyRepository.delete(body);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
