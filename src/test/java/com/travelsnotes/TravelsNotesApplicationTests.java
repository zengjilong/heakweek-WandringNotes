package com.travelsnotes;

import com.travelsnotes.dao.HKMapper;
import com.travelsnotes.pojo.Article;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.service.ArticleService;
import com.travelsnotes.service.UserServiceImpl;
import com.travelsnotes.util.TodayLoginUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class TravelsNotesApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    HKMapper mapper;
    @Test
    void contextLoadssss() {
        System.out.println(mapper.setUsername("zengji",1));
        System.out.println(mapper.setPhoneNumber("13117991309",1));

    }
    @Test
    void contextLoadsss() {
        System.out.println(mapper.getActiveDays(1));
        System.out.println(mapper.getTxtNum(1));
        System.out.println(mapper.getUsername(1));
    }

    @Test
    void contextLoadss() {
//        System.out.println(userService.setRecentLogin(1, new Date()));
        UserInfo userInfo = userService.queryById(1);
        Date login = userInfo.getRecentlyLogin();
        System.out.println(login);
       if (TodayLoginUtil.getToday(login)){
            userService.addActiveDays(1);
        }

    }
    @Test
    void contextLoads() {
      //  userService.registerUser("zeng","12345","12345678900");
        //userService.registerUser(new UserInfo("roii","12345","12345678900"));
        System.out.println(userService.queryById(611211));
        System.out.println(userService.queryByName("root"));
        UserInfo uu=new UserInfo();
        uu.setUserName("ijiii");
        uu.setPassword("12134");
        System.out.println(userService.registerUser(uu));
        System.out.println(uu);
    }
    @Test
    void articleTest(){
        for (int i = 0; i < 40; i++) {
            Article article1 = new Article("新云"+i, "记录美好时刻", 1, "c:/picture", new Date(), 6);
            articleService.addArticle(article1);
            //System.out.println(articleService.getById(article1.getArticleId()));
            System.out.println(article1);
        }
//        List<Article> list = articleService.listArticle(2);
//        for (Article article : list) {
//            System.out.println(article);
//        }
//        System.out.println(article1);
       // articleService.deleteById(article1.getArticleId());
    }

}
