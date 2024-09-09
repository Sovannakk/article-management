package com.kshrd.articlemanagement.service.serviceimpl;

import com.kshrd.articlemanagement.exception.NotFoundException;
import com.kshrd.articlemanagement.model.Article;
import com.kshrd.articlemanagement.model.ArticleRequest;
import com.kshrd.articlemanagement.repository.ArticleRepository;
import com.kshrd.articlemanagement.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("The article id " + id + " has not been founded."));
    }

    @Override
    public Article saveArticle(ArticleRequest articleRequest) {
        return articleRepository.save(articleRequest.toEntity());
    }

    @Override
    public Article updateArticle(Long id, ArticleRequest articleRequest) {
        getArticleById(id);
        return articleRepository.save(articleRequest.toEntity(id));
    }

    @Override
    public void deleteArticle(Long id) {
        getArticleById(id);
        articleRepository.deleteById(id);
    }
}
