package com.travelsnotes.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelsnotes.pojo.Article;
import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.ResultPage;
import com.travelsnotes.service.ArticleService;
import com.travelsnotes.util.DateConverterConfig;
import com.travelsnotes.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    FileUploadUtil fileUtil;
    @Autowired
    DateConverterConfig dateConverter;
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @CrossOrigin
    Result createArticle(@RequestPart(value = "file",required = false) MultipartFile file){
        System.out.println(file);
        if(file==null) return Result.error();
        System.out.println(file.getSize());
        return Result.ok();
    }
    @RequestMapping(value = "/addArticle",method = RequestMethod.POST)
    @CrossOrigin    //@RequestBody Map<String,Object> params,HttpServletRequest request ){
    Result createArticle( @RequestPart(value = "img",required = false) MultipartFile img,
                          @RequestParam(value = "token",required = false) String token,
                          @RequestParam(value = "title",required = false) String title,
                          @RequestParam(value = "content",required = false) String content,
                          HttpServletRequest request){
        try {
            if (token==null||token.length()==0) return Result.error(ResultCodeEnum.FAIL_TOKENNONULL);
            Object attribute = request.getSession().getAttribute(token);
            System.out.println(token);
            if (attribute == null) {
                return Result.error(ResultCodeEnum.FAIL_TOKENNOFINDED);     //token未找到 20010
            }
            System.out.println(111);
            String imgUrl= fileUtil.uploadPage(request, img);
            System.out.println(imgUrl);
            boolean flag=false;
            if (imgUrl==null) flag=true;
            int userId=(int)attribute;
//            String title = (String) params.get("title");
            if (title==null||title.length()==0) title="记录今日的美好";
//            Date date1=null;
////            String date = (String) params.get("date");
//            if (date==null||date.length()==0) date1=new Date();
//            else date1=dateConverter.convert(date);
//            System.out.println(date);
////            String content = (String) params.get("content");
            Article article = new Article(title, content,imgUrl,new Date(), userId);
            articleService.addArticle(article);
            if (flag) return Result.error(ResultCodeEnum.FAIL_SAVEFILE); //添加手记成功但图片存取失败
            return Result.ok(ResultCodeEnum.SUCCESS_ADDARTICLE) ; //添加文章成功
        }catch (Exception e){
           return Result.error(ResultCodeEnum.PARAM_ERROR); //参数不正确
        }
    }

    @RequestMapping(value = "/listArticle",method = RequestMethod.POST)
    @CrossOrigin
   public ResultPage list(@RequestParam(value = "token") String token,
                          @RequestParam(value = "start",required = false,defaultValue = "0")int start,
                          @RequestParam(value = "start",required = false,defaultValue = "30")int size,
                          HttpServletRequest request)throws Exception{
        try {
//           String token=params.get("token");
            System.out.println(token);
            Object attribute = request.getSession().getAttribute(token);
            System.out.println(attribute);
            if (attribute == null) {
               return ResultPage.error(ResultCodeEnum.FAIL_TOKENNOFINDED); //token未找到
           }
           int userId = (int)attribute;
//           int start=0;
//           String start1 = params.get("start");
//           if (start1==null||start1.length()==0) start=0;
//           else start=Integer.valueOf(params.get("start"));
//           String size1 = params.get("size");
//           int size=30;
//           if (size1==null||size1.length()==0) size=30;
//           else size=Integer.valueOf(params.get("size"));
           PageHelper.startPage(start, size, "articleId desc");
           List<Article> articleList = articleService.listArticle(userId);
           PageInfo<Article> page = new PageInfo<>(articleList);
           Map<String, PageInfo<Article>> map = new HashMap<>();
           map.put("page",page);
            return  ResultPage.ok(ResultCodeEnum.SUCCESS).pageData(map);
        }catch (Exception e){
           return ResultPage.error(ResultCodeEnum.PARAM_ERROR);
        }
        }

}
