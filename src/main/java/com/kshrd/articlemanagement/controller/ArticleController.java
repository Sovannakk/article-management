package com.kshrd.articlemanagement.controller;

import com.kshrd.articlemanagement.model.Article;
import com.kshrd.articlemanagement.model.dto.apiresponse.ApiResponse;
import com.kshrd.articlemanagement.model.dto.request.ArticleRequest;
import com.kshrd.articlemanagement.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Article>>> getAllArticles() {
        return ResponseEntity.ok(
                ApiResponse.<List<Article>>builder()
                        .message("Get all articles successfully")
                        .payload(articleService.getAllArticles())
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<Article>builder()
                        .message("Get article successfully")
                        .payload(articleService.getArticleById(id))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Article>> createArticle(@RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Article>builder()
                        .message("Create article successfully")
                        .payload(articleService.saveArticle(articleRequest))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> updateArticle(@PathVariable Long id, @RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.ok(
                ApiResponse.<Article>builder()
                        .message("Update article successfully")
                        .payload(articleService.updateArticle(id, articleRequest))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(
                ApiResponse.<Article>builder()
                        .message("Delete article successfully")
                        .payload(null)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}