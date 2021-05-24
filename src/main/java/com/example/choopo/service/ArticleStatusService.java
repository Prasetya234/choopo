package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.ArticleStatus;

import java.util.List;
import java.util.Map;

public interface ArticleStatusService {

    List<ArticleStatus> getAll();

    ArticleStatus getArticleStatusById(Long articleStatusId) throws ResourceNotFoundExceotion;

    ArticleStatus createArticleStatus(ArticleStatus articleStatusRequire);

    ArticleStatus updateArticleStatusById(Long articleStatusId,ArticleStatus articleStatusDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteArticleStatusById(Long articleStatusId) throws ResourceNotFoundExceotion;



}
