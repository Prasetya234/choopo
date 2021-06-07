package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.ArticleStatus;

import java.util.List;
import java.util.Map;

public interface ArticleStatusService {

    List<ArticleStatus> getAll();

    ArticleStatus getArticleStatusById(Long articleStatusId) throws ResourceNotFoundExceotion;

    ArticleStatus createArticleStatus(ArticleStatus articleStatusRequire);

    ArticleStatus updateArticleStatusById(Long articleStatusId,ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteArticleStatusById(Long articleStatusId) throws ResourceNotFoundExceotion;



}
