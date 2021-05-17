package com.example.choopo.repository;

import com.example.choopo.model.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ArticleStatusRepository extends JpaRepository<ArticleStatus, Long> {

}
