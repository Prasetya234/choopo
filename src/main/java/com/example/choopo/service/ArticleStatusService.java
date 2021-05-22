package com.example.choopo.service;

import com.example.choopo.dto.ArticleStatusDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.repository.ArticleStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleStatusService {

    @Autowired private ArticleStatusRepository articleStatusRepository;

    @Autowired private ModelMapper modelMapper;

    // POST ARTICLE STATUS
    public ArticleStatusDTO createArticleStatus(ArticleStatusDTO articleStatusDTO) {

        // convert dto to entity
        ArticleStatus articleStatus = mapToEntity(articleStatusDTO);
        ArticleStatus newArticleStatus = articleStatusRepository.save(articleStatus);

        // convert entity to dto
        ArticleStatusDTO postResponse = mapToDTO(newArticleStatus);
        return postResponse;
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
    public ResponseEntity<ArticleStatusDTO> getArticleStatusById(Long article_status_id) throws ResourceNotFoundExceotion {
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
    public ArticleStatusDTO updateArticleStatus(Long article_status_id, ArticleStatusDTO articleStatusDTODetils) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(article_status_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND " + article_status_id));

        articleStatus.setArticleStatusName(articleStatusDTODetils.getArticleStatusName());
        articleStatus.setArticleStatusCode(articleStatusDTODetils.getArticleStatusCode());
        final ArticleStatus updateArticleStatus = articleStatusRepository.save(articleStatus);

        return mapToDTO(updateArticleStatus);
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

    // CONVERT DTO TO ENTITY
    private ArticleStatusDTO mapToDTO(ArticleStatus articleStatus) {
        ArticleStatusDTO articleStatusDTO = modelMapper.map(articleStatus, ArticleStatusDTO.class);

        return  articleStatusDTO;
    }


    // CONVERT DTO TO ENTITY
    private ArticleStatus mapToEntity(ArticleStatusDTO articleStatusDTO) {
        ArticleStatus articleStatus = modelMapper.map(articleStatusDTO, ArticleStatus.class);

        return  articleStatus;
    }
}
