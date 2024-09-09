package com.kshrd.articlemanagement.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleRequest {

    private String title;
    private String content;
    private String imageUrl;
    private String author;
    private LocalDate publishedDate;
    private Integer views;
    private Boolean isPublished;

    public Article toEntity(){
        return new Article(null, this.title, this.content, this.imageUrl, this.author, this.publishedDate, this.views, this.isPublished);
    }

    public Article toEntity(Long id){
        return new Article(id, this.title, this.content, this.imageUrl, this.author, this.publishedDate, this.views, this.isPublished);
    }

}
