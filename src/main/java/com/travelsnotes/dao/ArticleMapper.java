package com.travelsnotes.dao;

import com.travelsnotes.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticleMapper {
    public void addArticle(Article article);
    public void deleteById(int articleId);
    public Article getById(int articleId);
    public int update(Article article);
    public List<Article> listArticle(int userId);
}
