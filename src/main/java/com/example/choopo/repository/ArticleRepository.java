package com.example.choopo.repository;

import com.example.choopo.model.Article;
import com.example.choopo.model.Body;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value="select a.* from article a", nativeQuery = true)
    List<Article> findByTitle( Pageable pageable);
}
