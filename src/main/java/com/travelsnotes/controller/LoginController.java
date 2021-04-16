package com.travelsnotes.controller;


import com.google.gson.JsonObject;
import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.service.UserServiceImpl;
import com.travelsnotes.util.TodayLoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")        //测试  用户登录
@CrossOrigin
public class LoginController {
    @Autowired
    UserServiceImpl userService;

    // 登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @CrossOrigin
    public Result Login(@RequestBody UserInfo userInfo, HttpServletRequest request){
        try {
//            System.out.println(json.get("userName"));
//            System.out.println(json.get("password"));
//            String  userName =  json.get("userName").getAsString();
//            String password=  json.get("password").getAsString();
//            String userName = params.get("userName");
////          String password = params.get("password");
            String password = userInfo.getPassword();
            String userName = userInfo.getUserName();
            System.out.println(userName);
            System.out.println(password);
            UserInfo user = userService.queryByName(userName);
            Map<String, Object> map = new HashMap<>();
            if (user != null ){
                System.out.println(111);
                if (user.getPassword().equals(password)){
                String token = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
                request.getSession().setAttribute(token, user.getUserId());
                map.put("token", token);
                Date date = user.getRecentlyLogin();
                    System.out.println(date);
                if (date==null||!TodayLoginUtil.getToday(date)){    //如果当天未登录
                    userService.addActiveDays(user.getUserId());
                }
                userService.setRecentLogin(user.getUserId(),new Date());
                 return Result.ok(ResultCodeEnum.SUCCESS_LOGIN).data(map);   //返回登陆成功 token
                } else {
                return  Result.error(ResultCodeEnum.ERROR_PASSWORD);    //密码错误
                }
            }else {
               return Result.error(ResultCodeEnum.ERROR_NOT_EXISTS_USER);   //用户名不存在
            }
        }catch (Exception e){
            return Result.error(ResultCodeEnum.PARAM_ERROR);    //参数不正确
        }
    }
}
