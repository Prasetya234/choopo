package com.example.choopo.web.repository;

import com.example.choopo.web.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT b.* FROM article b ORDER BY rand() LIMIT 5", nativeQuery = true)
    List<Article> articleScramble();

    @Query(value = "select c.* from article c order by c.total_view desc limit 10", nativeQuery = true)
    List<Article> articleTopTen();

    @Query(value = "select d.* from article d order by d.created_date desc limit 10", nativeQuery = true)
    List<Article> articleLatestNews();

    //   ------ ANONYMOUS QUERY ------

    @Query(value = "select e.* from article e where article_status_id = 1 and is_takedown = false and is_deleted = false", nativeQuery = true)
    List<Article> anonymousView();

    @Query(value = "select f.* from article where f.article_status_id  = 1 and is_deleted = false and is_takedown = false order by rand();", nativeQuery = true)
    List<Article> anonymousViewScramble();

    @Query(value = "select g.* from article g where g.article_id= :articleId and is_takedown = false and is_deleted = false", nativeQuery = true)
    Article anonymousViewById(Long articleId);

    @Query(value = "select h.* from article h where h.article_id= :articleId and is_takedown = false and is_deleted = true", nativeQuery = true)
    Article anonymousActiveAgain(Long articleId);

    @Query(value = "select i.* from article i where i.article_id= :article and is_takedown = false and is_deleted = false and article_status_id = 2",nativeQuery = true )
    Article anonymousPublicViews(Long article);

    @Query(value = "select j.* from article j where j.article_id= :articleId and is_takedown = true and is_deleted = false", nativeQuery = true)
    Article anonymousActivAgainTakedown(Long articleId);

}
