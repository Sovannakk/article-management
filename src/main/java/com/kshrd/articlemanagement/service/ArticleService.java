package com.kshrd.articlemanagement.service;

import com.kshrd.articlemanagement.model.Article;
import com.kshrd.articlemanagement.model.dto.request.ArticleRequest;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    Article getArticleById(Long id);

    Article saveArticle(ArticleRequest articleRequest);

    Article updateArticle(Long id, ArticleRequest articleRequest);

    void deleteArticle(Long id);
}
