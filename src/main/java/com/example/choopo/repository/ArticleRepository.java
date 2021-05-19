package com.example.choopo.repository;

import com.example.choopo.model.Article;
import com.example.choopo.model.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select a.* from article a", nativeQuery = true)
    List<Article> findByTitle(Pageable pageable);

    @Query(value = "select b.*  from article b order by random() limit 5", nativeQuery = true)
    List<Article> articleScramble();

    @Query(value = "select c.* from article c order by c.total_view desc limit 10", nativeQuery = true)
    List<Article> articleTopTen();

    @Query(value = "select d.* from article d order by d.created_date desc limit 10", nativeQuery = true)
    List<Article> articleLatestNews();

}
