package com.travelsnotes.service;


import com.travelsnotes.dao.ArticleMapper;
import com.travelsnotes.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleMapper articleMapper;

    //创建手记
    @Override
    public void addArticle(Article article) {
        articleMapper.addArticle(article);
    }

    //通过用户名获取所有手记
    @Override
    public List<Article> listArticle(int uerId) {
        return articleMapper.listArticle(uerId);
    }
    //通过手记id删除手记
    @Override
    public void deleteById(int articleId) {
        articleMapper.deleteById(articleId);
    }

    @Override
    public Article getById(int articleId) {
        return articleMapper.getById(articleId);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.update(article);
    }


}
