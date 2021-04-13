package com.travelnotes.lms1.controller;


import com.travelnotes.lms1.mapper.HKMapper;
import com.travelnotes.lms1.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    private HKMapper userProperties;

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

}
