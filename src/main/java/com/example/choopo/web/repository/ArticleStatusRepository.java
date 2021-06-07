package com.example.choopo.web.repository;

import com.example.choopo.web.model.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ArticleStatusRepository extends JpaRepository<ArticleStatus, Long> {

}
