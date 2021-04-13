package com.travelsnotes.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelsnotes.pojo.Article;
import com.travelsnotes.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    Map<String,Integer> createArticle(Article article){
        Map<String,Integer> map=new HashMap<>();
        try {
            articleService.addArticle(article);
            map.put("status",200);
            return map;
        }catch (Exception e){
            map.put("status",404);
            return map;
        }

    }

    @RequestMapping("/list")
    PageInfo<Article> list(@RequestParam(value = "token") String token,
                           @RequestParam(value = "start",defaultValue = "0")int start,
                           @RequestParam(value = "size",defaultValue = "30")int size,
                           Model model,
                           HttpServletRequest request)throws Exception{
       try {
           Object attribute = request.getSession().getAttribute(token);
           if (attribute == null) {
               return null;
           }
           int userId = (int) attribute;
           PageHelper.startPage(start, size, "articleId desc");
           List<Article> articleList = articleService.listArticle(userId);
           PageInfo<Article> page = new PageInfo<>(articleList);
           model.addAttribute("page", page);
           return  page;
       }catch (Exception e){
           return null;
       }
    }

}
