package com.example.choopo.service;

import com.example.choopo.dto.ArticleStatusDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;
import com.example.choopo.repository.ArticleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleStatusImpl implements ArticleStatusService {

    @Autowired private ArticleStatusRepository articleStatusRepository;

//  @Autowired private ModelMapper modelMapper;

    @Override
    public List<ArticleStatus> getAll() {
        return articleStatusRepository.findAll();
    }

    @Override
    public ArticleStatus getArticleStatusById(Long articleStatusId) throws ResourceNotFoundExceotion {
        return articleStatusRepository.findById(articleStatusId).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
    }

    @Override
    public ArticleStatus createArticleStatus(ArticleStatus articleStatusRequire) {
        return articleStatusRepository.save(articleStatusRequire);
    }

    @Override
    public ArticleStatus updateArticleStatusById(Long articleStatusId, ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(articleStatusId).orElseThrow(()
                -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND") );

        articleStatus.setArticleStatusCode(articleStatusDetails.getArticleStatusCode());
        articleStatus.setArticleStatusName(articleStatusDetails.getArticleStatusName());
        final ArticleStatus updateData = articleStatusRepository.save(articleStatus);
        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteArticleStatusById(Long articleStatusId) throws ResourceNotFoundExceotion {
        ArticleStatus articleStatus = articleStatusRepository.findById(articleStatusId).orElseThrow(()
                -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));

        articleStatusRepository.deleteById(articleStatusId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }


//    // CONVERT DTO TO ENTITY
//    private ArticleStatusDTO mapToDTO(ArticleStatus articleStatus) {
//        ArticleStatusDTO articleStatusDTO = modelMapper.map(articleStatus, ArticleStatusDTO.class);
//
//        return  articleStatusDTO;
//    }
//
//
//    // CONVERT DTO TO ENTITY
//    private ArticleStatus mapToEntity(ArticleStatusDTO articleStatusDTO) {
//        ArticleStatus articleStatus = modelMapper.map(articleStatusDTO, ArticleStatus.class);
//
//        return  articleStatus;
//    }
}
