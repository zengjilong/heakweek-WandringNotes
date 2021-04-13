package com.travelsnotes.service;


import com.travelsnotes.pojo.Article;

import java.util.List;

public interface ArticleService {
    public void addArticle(Article article);
    public void deleteById(int articleId);
    public Article getById(int articleId);
    public int updateArticle(Article article);
    public List<Article> listArticle(int UserId);
}
