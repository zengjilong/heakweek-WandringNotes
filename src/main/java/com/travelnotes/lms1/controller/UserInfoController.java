package com.travelnotes.lms1.controller;


import com.travelnotes.lms1.mapper.HKMapper;
import com.travelnotes.lms1.pojo.UserInfo;
import com.travelnotes.lms1.server.OSSutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    private HKMapper userProperties;

    @Autowired
    private OSSutil ossProperties;


    //设置用户信息
    @PostMapping("/updateInfo")
    public String setUserInfo(UserInfo userInfo){
        int tempId = 1;
        try{
            userProperties.setUsername(userInfo.getPhoneNumber(), tempId);
            userProperties.setPhoneNumber(userInfo.getUserName(), tempId);
            userProperties.setAvatar(userInfo.getAvatarUrl(), tempId);
        }catch(Exception e){
            return "Catch Exception";
        }
        return "Result";
    }

    //设置用户头像URL
    @PostMapping("/uploadAvatar")
    public String setUserAvatar(UserInfo userInfo){
        int tempId = 1;
        try{
            if(ossProperties.setAvatar(userInfo.getAvatarUrl(), tempId)){
                return "Result! Get";
            }else{
                return "Failed!";
            }
        }catch(Exception e){
            return "Catch Exception";
        }
    }

    //返回用户头像URL
    @GetMapping("/user/getUserAvatar")
    public String getUserInfo(int id){
        return ossProperties.getAvatar(id);
    }

}
