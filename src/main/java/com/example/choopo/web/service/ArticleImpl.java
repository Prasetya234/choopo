package com.example.choopo.web.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Article;
import com.example.choopo.web.repository.ArticleRepository;
import com.example.choopo.web.repository.ArticleStatusRepository;
import com.example.choopo.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleImpl implements ArticleService{

    @Autowired private ArticleRepository articleRepository;

    @Autowired private ArticleStatusRepository articleStatusRepository;

    @Autowired private CategoryRepository categoryRepository;

    @Override
    public Article createArticle(Article articleRequire) throws ResourceNotFoundExceotion {

        Article article = new Article();
        article.setSubtitle(articleRequire.getSubtitle());
        article.setTitle(articleRequire.getTitle());
        article.setMainImage(articleRequire.getMainImage());
        article.setTopic(articleRequire.getTopic());
        article.setArticleStatus("2");
        article.setCategory(articleRequire.getCategory());
        article.setTakedown(false);
        article.setDeleted(false);

        Article articleStatus = articleStatusRepository.findById(Long.valueOf(article.getArticleStatus()))
                .map(articleStatus1 -> {
                    articleRequire.setArticleStatusId(articleStatus1);
                    return articleRequire;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
        Article category = categoryRepository.findById(Long.valueOf(articleRequire.getCategory()))
                .map(category1 -> {
                    articleRequire.setCategoryId(category1);
                    return articleStatus;
                }).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));
        return articleRepository.save(articleRequire);
    }

    @Override
    public List<Article> getAllArticle(int page, int size) {
        List<Article> tutorials = new ArrayList<Article>();
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Article> articleList = articleRepository.findAll(pageRequest);
        tutorials = articleList.getContent();

        return tutorials;
    }

    @Override
    public List<Article> findLatestNews() {
        return articleRepository.articleLatestNews();
    }

    @Override
    public List<Article> findTopTen() {
        return articleRepository.articleTopTen();
    }

    @Override
    public List<Article> findMathRandom() {
        return articleRepository.articleScramble();
    }

    @Override
    public Article getArticleById(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND"));
        article.setTotalView(article.getTotalView() + 1);
        final Article totalView = articleRepository.save(article);
        return totalView;
    }

    @Override
    public Article updateArticle(Long articleId, Article articleDetails) throws ResourceNotFoundExceotion {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("ARTICLE ID NOT FOUND"));

        article.setSubtitle(articleDetails.getSubtitle());
        article.setTitle(articleDetails.getTitle());
        article.setMainImage(articleDetails.getMainImage());
        article.setTopic(articleDetails.getTopic());
        article.setCategory(articleDetails.getCategory());

        Article category1 = categoryRepository.findById(Long.valueOf(articleDetails.getCategory())).map(category -> {
            articleDetails.setCategoryId(category);
            return articleDetails;
        }).orElseThrow(() ->
                new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));
        article.setCategoryId(category1.getCategoryId());
        final Article updateData = articleRepository.save(article);

        return updateData;
    }

    @Override
    public Article deleteArticle(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.anonymousViewById(articleId);
        if (article == null) {
            throw new ResourceNotFoundExceotion("KEGAGALAN MEMUAT DATA COBA PERIKSA LAGI DATA ANDA");
        }
        article.setDeleted(true);
        return articleRepository.save(article);
    }

    @Override
    public Article getArticlePublikasi(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.anonymousPublicViews(articleId);
        if (article == null) {
            throw new ResourceNotFoundExceotion("ARTICLE TELAH AKTIF");
        }
        article.setArticleStatus("1");
        return articleStatusRepository.findById(Long.valueOf(article.getArticleStatus())).map(articleStatus -> { article.setArticleStatusId(articleStatus);
            return articleRepository.save(article);
        }).orElseThrow(() ->
                new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
    }

    @Override
    public Article deleteArticleTakedown(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.anonymousViewById(articleId);
        if (article == null) {
            throw new ResourceNotFoundExceotion("KEGAGALAN MEMUAT DATA COBA PERIKSA LAGI DATA ANDA");
        }
        article.setTakedown(true);
        return articleRepository.save(article);
    }

    @Override
    public Article takedownReactivate(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.anonymousActivAgainTakedown(articleId);
        if (article == null) {
            throw new ResourceNotFoundExceotion("KEGAGALAN MEMUAT DATA COBA PERIKSA LAGI DATA ANDA");
        }
        article.setTakedown(false);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAnonymousArticle() throws  ResourceNotFoundExceotion{
        List<Article> article = articleRepository.anonymousView();
        if (article == null ) {
            throw new ResourceNotFoundExceotion("ARTICLE KOSONG");
        }
        return article;
    }

    @Override
    public List<Article> getAnonymousScramble() {
        return articleRepository.anonymousViewScramble();
    }

    @Override
    public Article getAnonymousById(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.anonymousViewById(articleId);
        if (article == null) {
            throw new ResourceNotFoundExceotion("ARTICLE ID TIDAK DITEMUKAN");
        }
        article.setTotalView(article.getTotalView() + 1);
        return articleRepository.save(article);
    }

    @Override
    public Article getAnonymousActiveAgain(Long articleId) throws ResourceNotFoundExceotion {
        Article article = articleRepository.anonymousActiveAgain(articleId);
        if (article == null) {
            throw new ResourceNotFoundExceotion("PEMULIHAN ARTICLE ID TIDAK DI TEMUKAN");
        }
        article.setDeleted(false);
        return articleRepository.save(article);
    }

}
