package com.travelsnotes.controller;


import com.travelsnotes.pojo.Result;
import com.travelsnotes.pojo.ResultCodeEnum;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.pojo.UserInfo;
import com.travelsnotes.service.UserService;
import com.travelsnotes.util.MD5Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    MD5Util md5Util;
    @RequestMapping(value = "/isHas",method = RequestMethod.GET)
    @CrossOrigin
    public Result isHas(String userName){
        try {
            UserInfo user = userService.queryByName(userName);
            if (user != null) {
               return Result.error(ResultCodeEnum.ERROR_IS_EXISTS_USER);   //返回用户已存在
            } else {
                return Result.ok(ResultCodeEnum.SUCCESS_USABLE); //返回可以使用该用户名
            }
        }catch (Exception e){
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
    }
    @RequestMapping(value = "/account",method = RequestMethod.POST)
    @CrossOrigin
    public Result register(@RequestParam(value = "userName" ,required = false) String userName,
                           @RequestParam(value = "password",required = false) String password){
        try{
//            UserInfo userInfo=new UserInfo(userName,password,phoneNumber);
//            String userName = params.get("userName");
//            String password=params.get("password");
            if (userName==null||userName.length()==0) return Result.error(ResultCodeEnum.FAIL_USERNOTNULL);
            if (password==null||password.length()==0) return Result.error(ResultCodeEnum.FAIL_PASSWORDNOTNULL);
            UserInfo userInfo = userService.queryByName(userName);
            if (userInfo!=null) return Result.error(ResultCodeEnum.FAIL_REGISTER);
            userService.registerUser(userName,md5Util.EncoderByMd5(password));
            return Result.ok(ResultCodeEnum.SUCCESS_REGISTER); //注册成功
        }catch (Exception e){
            return    Result.error(ResultCodeEnum.FAIL_REGISTER);  //注册失败
        }
    }
}
